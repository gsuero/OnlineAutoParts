package com.autoservice.exceptions;

public class ConfirmationMailException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public ConfirmationMailException() {

	}

	public ConfirmationMailException(String arg0) {
		super(arg0);
	}

	public ConfirmationMailException(Throwable arg0) {
		super(arg0);
	}

	public ConfirmationMailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
