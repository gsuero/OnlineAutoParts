package com.autoservice.exceptions;

public class InvalidUserAndPasswordException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	private String message;
	public InvalidUserAndPasswordException() {

	}

	public InvalidUserAndPasswordException(String arg0) {
		setMessage(arg0);
	}

	public InvalidUserAndPasswordException(Throwable arg0) {
		super(arg0);
	}

	public InvalidUserAndPasswordException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
