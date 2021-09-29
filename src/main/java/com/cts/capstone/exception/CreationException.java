package com.cts.capstone.exception;

public class CreationException extends RuntimeException {

	public CreationException(String type, long id, Throwable cause) {
		this(type, String.valueOf(id), cause);
	}

	public CreationException(String type, String id, Throwable cause) {
		super("Could not create " + type + " " + id + ". That id is already in use", cause);
	}

}
