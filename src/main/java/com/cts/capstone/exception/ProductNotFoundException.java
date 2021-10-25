package com.cts.capstone.exception;

public class ProductNotFoundException extends RuntimeException {

	private final static String message = "Could not find product";

	public ProductNotFoundException() {
		super(message);
	}

	public ProductNotFoundException(long id) {
		this(id, null);
	}

	public ProductNotFoundException(long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
