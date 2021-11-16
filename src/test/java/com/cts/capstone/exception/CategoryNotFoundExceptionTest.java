package com.cts.capstone.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryNotFoundExceptionTest {

	private final static String message = "Could not find category";
	CategoryNotFoundException ex;

	@Test
	void def() {
		ex = new CategoryNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new CategoryNotFoundException(123L);

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new CategoryNotFoundException(123L, new Exception("error"));

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}