package com.autoservices.web.action.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.autoservice.commons.Constants;
import com.autoservices.web.session.SessionHelper;
import com.autoservices.web.session.UserSession;
import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	public String execute() throws Exception {
		UserSession user = null;
		String username = null;
		if (session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) != null) {
			user = (UserSession) session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			username = user.getLogin();
			session.removeAttribute(Constants.GLOBAL_USER_SESSION_KEY);
		}
		SessionHelper helper = new SessionHelper(request, response);
		if (username != null) {
			helper.rememberMeOff(username);
		}
		//if (session != null)
			//session.invalidate();
		
		return SUCCESS;
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
