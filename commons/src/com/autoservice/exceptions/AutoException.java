package com.autoservice.exceptions;

public class AutoException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public AutoException() {

	}

	public AutoException(String arg0) {
		super(arg0);
	}

	public AutoException(Throwable arg0) {
		super(arg0);
	}

	public AutoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
