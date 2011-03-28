package com.autoservices.web.action;

import com.autoservice.ejbs.user.UserBean;
import com.autoservice.ejbs.user.UserBeanLocal;
import com.autoservice.exceptions.InvalidUserAndPasswordException;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private boolean remember;
	private String simpleMessage;

	public String execute() throws Exception {
		
		try {
			UserBeanLocal userBean = new UserBean();
			if(userBean.isValidUserAndPassword(getLogin(), getPassword())){
				addActionMessage("Inicio de sesion correctamente");
				return SUCCESS;
			}
		} catch (InvalidUserAndPasswordException ex) {
			addActionError(ex.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
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

}
