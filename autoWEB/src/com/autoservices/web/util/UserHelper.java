package com.autoservices.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.autoservice.commons.Constants;
import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.entities.User;
import com.autoservice.entities.Vehicle;
import com.autoservice.exceptions.AutoException;
import com.autoservice.exceptions.NotAuthorizedException;
import com.autoservice.exceptions.NotExistsException;
import com.autoservices.web.session.SessionHelper;
import com.autoservices.web.session.UserSession;

public class UserHelper {

	public static boolean authenticateUser(HttpServletRequest request,
			String login, String password) {
		boolean authenticated =  false;
		try {
			UserBeanLocal userBean = new UserBean();
			if (userBean.isValidUserAndPassword(login, password)) {
				User user = userBean.getUser(login);
				request.getSession().setAttribute(Constants.GLOBAL_USER_SESSION_KEY, getUserSession(user, request));
				authenticated = true;
			}
		} catch (Exception ex) {
			authenticated = false;
		}

		return authenticated;
	}
	
	public static boolean authenticateUser(HttpServletRequest request,String login) {
		boolean retorno = false;
		try {
			UserBeanLocal userBean = new UserBean();
			User user = userBean.getUser(login);
			if (user != null && user.getStatus() == Constants.USER_STATUS_ACTIVE) {
				request.getSession().setAttribute(Constants.GLOBAL_USER_SESSION_KEY, getUserSession(user, request));
				retorno = true;
			}
		} catch (Exception ex) {
			retorno = false;
		}

		return retorno;
	}
	
	private static UserSession getUserSession(User user, HttpServletRequest request) {
		UserSession userSession = new UserSession();
		userSession.setAdmin(Constants.GLOBAL_YES_RESPONSE.equalsIgnoreCase(user.getUserType().getAdmin() + ""));
		userSession.setEmail(user.getEmail());
		userSession.setFirstName(user.getFirstName());
		userSession.setLastName(user.getLastName());
		userSession.setLogin(user.getLogin());
		userSession.setSalutation(user.getSalutation());
		userSession.setSessionId(request.getSession().getId());
		return userSession;
	}
	
	public static boolean isUserAuthenticated(HttpServletRequest request, HttpServletResponse response) throws NotAuthorizedException {
	    HttpSession session =  request.getSession ();

		//Object request = invocation.getInvocationContext().;
		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			try {
				SessionHelper helper = new SessionHelper(request, response);
				if(!helper.imRemembered()) {
					return false;
				} else {
					try { 
						helper.logUserRemembered();
					} catch (Exception ex) {
						throw new NotAuthorizedException("No existe ningun usuario.");
					}
				}
			} catch (NotExistsException ex) {
				throw new NotAuthorizedException("No existe ningun usuario.");
			}
		}
		return true;
		
	}
	
   public static void removeVehicle(User user, String chassis) throws AutoException {
	   UserBeanLocal userBean = new UserBean();
		if (user.getVehicles() != null) {
			for(int a=0;a<user.getVehicles().size();a++) {
				Vehicle ve = user.getVehicles().get(a);
				if (chassis.equalsIgnoreCase(ve.getChassis())) {
					userBean.removeVehicle(chassis);
					break;
				}
			}
		}
   }
}
