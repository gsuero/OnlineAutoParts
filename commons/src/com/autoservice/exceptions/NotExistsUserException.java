package com.autoservice.exceptions;

public class NotExistsUserException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public NotExistsUserException() {

	}

	public NotExistsUserException(String arg0) {
		super(arg0);
	}

	public NotExistsUserException(Throwable arg0) {
		super(arg0);
	}

	public NotExistsUserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
