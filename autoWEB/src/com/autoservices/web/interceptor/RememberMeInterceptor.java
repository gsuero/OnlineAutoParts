package com.autoservices.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.autoservice.commons.Constants;
import com.autoservices.web.session.SessionHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RememberMeInterceptor extends AbstractInterceptor  {
	static Logger logger = Logger.getLogger(RememberMeInterceptor.class.getName());
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		String METHOD_NAME = "intercept";
		logger.info(METHOD_NAME + " entrando");
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    HttpServletResponse response = (HttpServletResponse) context.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	    HttpSession session =  request.getSession(true);

		//Object request = invocation.getInvocationContext().;
		if((session.getAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY) == null 
				|| ((Boolean)session.getAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY)) == true) 
				&& session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
		
			logger.info(METHOD_NAME + " entrando");
			
			SessionHelper helper = new SessionHelper(request, response);
			if(helper.imRemembered()) {
				try { 
					helper.logUserRemembered();
					session.setAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY, true);
				} catch (Exception ex) {
					ex.printStackTrace();
					// tener cuidado aqui, esto podria lanzar siempre exceptiones cuando existen las cookie y en el
					// procedimiento existan errores...
				}
			} else {
				session.setAttribute(Constants.GLOBAL_REMEMBER_SESSION_KEY, false);
			}
		} 
		return invocation.invoke();
	}
}
