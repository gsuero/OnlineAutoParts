package com.autoservice.exceptions;

public class NotAuthorizedException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public NotAuthorizedException() {

	}

	public NotAuthorizedException(String arg0) {
		super(arg0);
	}

	public NotAuthorizedException(Throwable arg0) {
		super(arg0);
	}

	public NotAuthorizedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
