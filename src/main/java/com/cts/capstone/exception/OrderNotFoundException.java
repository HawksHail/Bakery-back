package com.cts.capstone.exception;

public class OrderNotFoundException extends RuntimeException {

	private final static String message = "Could not find order";

	public OrderNotFoundException() {
		super(message);
	}

	public OrderNotFoundException(Long id) {
		this(id, null);
	}

	public OrderNotFoundException(Long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
