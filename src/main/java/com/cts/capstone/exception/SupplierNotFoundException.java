package com.cts.capstone.exception;

public class SupplierNotFoundException extends RuntimeException {

	private final static String message = "Could not find supplier";

	public SupplierNotFoundException() {
		super(message);
	}

	public SupplierNotFoundException(Long id) {
		this(id, null);
	}

	public SupplierNotFoundException(Long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
