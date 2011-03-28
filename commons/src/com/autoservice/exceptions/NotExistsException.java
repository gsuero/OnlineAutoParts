package com.autoservice.exceptions;

public class NotExistsException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public NotExistsException() {

	}

	public NotExistsException(String arg0) {
		super(arg0);
	}

	public NotExistsException(Throwable arg0) {
		super(arg0);
	}

	public NotExistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
