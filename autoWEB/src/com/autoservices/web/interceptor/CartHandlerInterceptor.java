package com.autoservices.web.interceptor;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ParameterAware;

import com.autoservice.commons.Constants;
import com.autoservices.web.session.SessionHelper;
import com.autoservices.web.util.CartUtil;
import com.autoservices.web.util.DatalayerUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CartHandlerInterceptor extends AbstractInterceptor  {
	private static final long serialVersionUID = 1L;
	public String intercept(ActionInvocation invocation) throws Exception {
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    //HttpServletResponse response = (HttpServletResponse) context.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
	    HttpSession session =  request.getSession(true);

	    try {
		//Object request = invocation.getInvocationContext().;
				CartUtil cartUtil = new CartUtil(request);
    			int count = cartUtil.getShoppingCartCount();
    			session.setAttribute(Constants.GLOBAL_CART_COUNT_SESSION_KEY, count);
    			/*request.setAttribute(Constants.GLOBAL_CART_COUNT_SESSION_KEY, count);
    			response.setAttribute(Constants.GLOBAL_CART_COUNT_SESSION_KEY, count);*/
	    } catch (NullPointerException ex) {
	    	ex.printStackTrace();
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
		return invocation.invoke();
	}
}
