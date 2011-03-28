package com.autoservice.exceptions;

public class InvalidOperationException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public InvalidOperationException() {

	}

	public InvalidOperationException(String arg0) {
		super(arg0);
	}

	public InvalidOperationException(Throwable arg0) {
		super(arg0);
	}

	public InvalidOperationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
