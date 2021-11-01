package com.cts.capstone.exception;

public class CustomerNotFoundException extends RuntimeException {

	private final static String message = "Could not find customer";

	public CustomerNotFoundException() {
		super(message);
	}

	public CustomerNotFoundException(Long id) {
		this(id, null);
	}

	public CustomerNotFoundException(Long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
