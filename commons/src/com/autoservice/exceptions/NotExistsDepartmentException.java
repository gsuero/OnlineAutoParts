package com.autoservice.exceptions;

public class NotExistsDepartmentException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public NotExistsDepartmentException() {

	}

	public NotExistsDepartmentException(String arg0) {
		super(arg0);
	}

	public NotExistsDepartmentException(Throwable arg0) {
		super(arg0);
	}

	public NotExistsDepartmentException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
