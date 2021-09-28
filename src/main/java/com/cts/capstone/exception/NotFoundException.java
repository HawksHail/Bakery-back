package com.cts.capstone.exception;

public class NotFoundException extends RuntimeException {


	public NotFoundException() {
		super();
	}

	public NotFoundException(String type, long id) {
		this(type, id, null);
	}

	public NotFoundException(String type, long id, Throwable cause) {
		this(type, String.valueOf(id), cause);
	}

	public NotFoundException(String type, String id, Throwable cause) {
		super("Could not find " + type + " " + id, cause);
	}

	public NotFoundException(String type, String id) {
		this(type, id, null);
	}

}
