package com.autoservice.exceptions;

public class InvalidItemException extends Exception {
	private static final long serialVersionUID = 4329249246646382746L;
	public InvalidItemException(String message) {
		super(message);
	}
}
