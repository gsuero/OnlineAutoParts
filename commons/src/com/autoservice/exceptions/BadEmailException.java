package com.autoservice.exceptions;

public class BadEmailException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public BadEmailException() {

	}

	public BadEmailException(String arg0) {
		super(arg0);
	}

	public BadEmailException(Throwable arg0) {
		super(arg0);
	}

	public BadEmailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
