package com.cts.capstone.exception;

public class OrderNotFoundException extends RuntimeException {

	private final static String message = "Could not find order";

	public OrderNotFoundException() {
		super(message);
	}

	public OrderNotFoundException(long id) {
		this(id, null);
	}

	public OrderNotFoundException(long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
