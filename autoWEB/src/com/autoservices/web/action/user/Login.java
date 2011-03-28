package com.autoservices.web.action.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.autoservice.commons.Constants;
import com.autoservices.web.session.SessionHelper;
import com.autoservices.web.util.UserHelper;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String login;
	private String password;
	private boolean remember;
	private String simpleMessage;

	public String check() throws Exception {
		
		return SUCCESS;
	}
	public String execute() throws Exception {
		
		try {
			boolean authenticated = UserHelper.authenticateUser(request, getLogin(), getPassword());
			if(authenticated){
				this.session = request.getSession(true);
				addActionMessage("Inicio de sesion correctamente");
				if (isRemember()) {
					SessionHelper helper = new SessionHelper(request, response);
					helper.rememberMe(getLogin());
					session.setAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY, true);
				} else {
					session.setAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY, false);
				}
				session.setMaxInactiveInterval(18000);
				return SUCCESS;
			} else {
				addActionError("Usuario o contrasñena invalidos.");
				return ERROR;
			}
		} catch (Exception ex) {
			addActionError(ex.getMessage());
			return ERROR;
		}
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public String getSimpleMessage() {
		return simpleMessage;
	}

	public void setSimpleMessage(String simpleMessage) {
		this.simpleMessage = simpleMessage;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = request.getSession();
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
}
