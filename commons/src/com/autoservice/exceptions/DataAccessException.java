package com.autoservice.exceptions;

public class DataAccessException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public DataAccessException() {

	}

	public DataAccessException(String arg0) {
		super(arg0);
	}

	public DataAccessException(Throwable arg0) {
		super(arg0);
	}

	public DataAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
