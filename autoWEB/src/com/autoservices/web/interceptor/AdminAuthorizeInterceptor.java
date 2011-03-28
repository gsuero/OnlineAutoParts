package com.autoservices.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.autoservice.commons.Constants;
import com.autoservice.entities.User;
import com.autoservices.web.session.SessionHelper;
import com.autoservices.web.session.UserSession;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AdminAuthorizeInterceptor extends AbstractInterceptor  {
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    HttpServletResponse response = (HttpServletResponse) context.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	    HttpSession session =  request.getSession ();

		//Object request = invocation.getInvocationContext().;
		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			SessionHelper helper = new SessionHelper(request, response);
			if(!helper.imRemembered()) {
				addActionError(invocation, "Debe iniciar sesión para poder accesar.");
				return Action.LOGIN;
			} else {
				try { 
					helper.logUserRemembered();
				} catch (Exception ex) {
					// tener cuidado aqui, esto podria lanzar siempre exceptiones cuando existen las cookie y en el
					// procedimiento existan errores...
					addActionError(invocation, "Cookies corruptas.");
					return Action.LOGIN;
				}
			}
		} else {
			UserSession userSession = (UserSession) session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			if (!userSession.isAdmin()) {
				return Action.LOGIN;
			}
		}
		return invocation.invoke();
	}
	

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

}
