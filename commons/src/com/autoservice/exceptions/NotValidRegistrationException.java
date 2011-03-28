package com.autoservice.exceptions;

public class NotValidRegistrationException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public NotValidRegistrationException() {

	}

	public NotValidRegistrationException(String arg0) {
		super(arg0);
	}

	public NotValidRegistrationException(Throwable arg0) {
		super(arg0);
	}

	public NotValidRegistrationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
