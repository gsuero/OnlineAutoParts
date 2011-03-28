package com.autoservices.web.session;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.autoservice.commons.security.PasswordBusiness;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.UserSession;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.util.UserHelper;

public class SessionHelper {
	static Logger logger = Logger.getLogger(SessionHelper.class.getName());
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String COOKIE_USER = "user";
	private String COOKIE_SERIE = "series";
	private String COOKIE_TOKEN = "token";
	
	public SessionHelper(HttpServletRequest request) {
		setRequest(request);
	}

	public SessionHelper(HttpServletResponse response) {
		setResponse(response);
	}
	
	public SessionHelper(HttpServletRequest request, HttpServletResponse response) {
		setRequest(request);
		setResponse(response);
	}
	public boolean imRemembered() throws NotExistsException {
		String METHOD_NAME = "imRemembered";
		logger.info(METHOD_NAME + " entrando");
		String cookieName = "";
		String username = null;
		String series = null;
		String token = null;
		boolean retorno =  false;
		Cookie cookie[] = getRequest().getCookies();
		logger.info(METHOD_NAME + " Cookies Length: " + cookie.length);
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				cookieName = cookie[i].getName();
				logger.info(METHOD_NAME + " Cookie " + cookieName + " cookie value: " + cookie[i].getValue());
				if (cookieName.equals(COOKIE_USER)) {
					username = cookie[i].getValue();
				} else if (cookieName.equals(COOKIE_SERIE)) {
					series = cookie[i].getValue();
				} else if (cookieName.equals(COOKIE_TOKEN)) {
					token = cookie[i].getValue();
				}
			}
		}
		logger.info(METHOD_NAME + " user: " + username + ", serie: " +series +", token: " + token);
		if(username != null && series != null && token != null) {
			try {
				UserBeanLocal userBean = new UserBean();
				String calculatedSerie = new PasswordBusiness(username).getMD5();
				logger.info(METHOD_NAME + " calculated serie: " + calculatedSerie);
				if (series.equals(calculatedSerie)) {
					logger.info(METHOD_NAME + " are equal: ");	
					if (userBean.getUserSession(series, token) != null) {
						logger.info(METHOD_NAME + " logged?");
						retorno = true;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new NotExistsException(ex.getMessage());
			}
		}
		return (retorno);
	}
	public void logUserRemembered() throws Exception {
		String METHOD_NAME = "logUserRemembered()";
		logger.info(METHOD_NAME + " entrando");
		Cookie cookie[] = getRequest().getCookies();
		String username = null;
		if (cookie != null && cookie.length > 0) {
			for (int i = 0; i < cookie.length; i++) {
				if (COOKIE_USER.equals(cookie[i].getName())) {
					username = cookie[i].getValue();
					logger.info(METHOD_NAME + " username : " + username);
					break;
				}
			}
			if (username != null && username.length() > 0) {
				UserHelper.authenticateUser(request, username);
				updateToken(username);
			}
		}
	}
	private String getTokenHash(String serieHash) throws Exception {
		return new PasswordBusiness(serieHash + (new Date().getTime()+"")).getMD5();
	}
	
	private String getSerieHash(String username) throws Exception {
		return new PasswordBusiness(username).getMD5();
	}
	
	private void setCookies(String username, String serieHash, String tokenHash) {
		Cookie user = new Cookie(COOKIE_USER, username);
		Cookie serie = new Cookie(COOKIE_SERIE, serieHash);
		Cookie token = new Cookie(COOKIE_TOKEN, tokenHash);
		serie.setPath(request.getContextPath());
		token.setPath(request.getContextPath());
		user.setPath(request.getContextPath());
		
		user.setMaxAge(8640000);
		serie.setMaxAge(8640000);
		token.setMaxAge(8640000);
		response.addCookie(user);
		response.addCookie(serie);
		response.addCookie(token);
		
	}
	public void updateToken(String username) throws Exception {
		String serieHash = getSerieHash(username);
		String tokenHash = getTokenHash(serieHash);
		setCookies(username,serieHash,tokenHash);
		try {
			UserBeanLocal userBean = new UserBean();
			UserSession userSession = userBean.getUserSessionByUserId(username);
			userSession.setSerie(serieHash);
			userSession.setToken(tokenHash);
			userSession.setUser(userBean.getUser(username));
			userBean.updateUserSession(userSession);
		} catch (Exception ex) {
			throw ex;
		}
	}
	public void rememberMe(String username) throws Exception {
		String serieHash = getSerieHash(username);
		String tokenHash = getTokenHash(serieHash);
		setCookies(username,serieHash,tokenHash);
		try {
			UserBeanLocal userBean = new UserBean();
			UserSession userSession = null;
			try {
				userSession = userBean.getUserSessionByUserId(username);
				userSession.setToken(tokenHash);
				userBean.updateUserSession(userSession);
			} catch (NotExistsException ex) {
				userSession = new UserSession();
				userSession.setSerie(serieHash);
				userSession.setToken(tokenHash);
				userSession.setUser(userBean.getUser(username));
				userBean.createUserSession(userSession);
			} 
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void rememberMeOff(String username) {
		Cookie unameCookie = new Cookie(COOKIE_USER, "expired");
		unameCookie.setMaxAge(0);
		response.addCookie(unameCookie);

		Cookie pwdCookie = new Cookie(COOKIE_SERIE, "expired");
		pwdCookie.setMaxAge(0);
		response.addCookie(pwdCookie);
		
		Cookie remember = new Cookie(COOKIE_TOKEN, "expired");
		pwdCookie.setMaxAge(0);
		response.addCookie(remember);
		try {
			UserBeanLocal userBean = new UserBean();
			userBean.deleteUserSession(userBean.getUserSessionByUserId(username));
		} catch (Exception ex) {
			System.out.println("LOggin an exception");
			ex.printStackTrace();
		}
	}

	protected String getCOOKIE_USER() {
		return COOKIE_USER;
	}

	protected void setCOOKIE_USER(String cOOKIEUSER) {
		COOKIE_USER = cOOKIEUSER;
	}

	protected String getCOOKIE_PASSWORD() {
		return COOKIE_SERIE;
	}

	protected void setCOOKIE_PASSWORD(String cOOKIEPASSWORD) {
		COOKIE_SERIE = cOOKIEPASSWORD;
	}

	protected String getCOOKIE_REMEMBER() {
		return COOKIE_TOKEN;
	}

	protected void setCOOKIE_REMEMBER(String cOOKIEREMEMBER) {
		COOKIE_TOKEN = cOOKIEREMEMBER;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getCOOKIE_SERIE() {
		return COOKIE_SERIE;
	}
	public void setCOOKIE_SERIE(String cOOKIESERIE) {
		COOKIE_SERIE = cOOKIESERIE;
	}
	public String getCOOKIE_TOKEN() {
		return COOKIE_TOKEN;
	}
	public void setCOOKIE_TOKEN(String cOOKIETOKEN) {
		COOKIE_TOKEN = cOOKIETOKEN;
	}
}
