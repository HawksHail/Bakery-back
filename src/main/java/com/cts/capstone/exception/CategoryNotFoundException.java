package com.cts.capstone.exception;

public class CategoryNotFoundException extends RuntimeException {

	private final static String message = "Could not find category";

	public CategoryNotFoundException() {
		super(message);
	}

	public CategoryNotFoundException(Long id) {
		this(id, null);
	}

	public CategoryNotFoundException(Long id, Throwable cause) {
		super(message + " " + id, cause);
	}
}
