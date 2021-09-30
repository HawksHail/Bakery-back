package com.cts.capstone.exception;

public class SupplierNotFoundException extends RuntimeException {

	private final static String message = "Could not find supplier";

	public SupplierNotFoundException() {
		super(message);
	}

	public SupplierNotFoundException(long id) {
		this(id, null);
	}

	public SupplierNotFoundException(long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
