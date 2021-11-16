package com.cts.capstone.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductNotFoundExceptionTest {

	private final static String message = "Could not find product";
	ProductNotFoundException ex;

	@Test
	void def() {
		ex = new ProductNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new ProductNotFoundException(123L);

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new ProductNotFoundException(123L, new Exception("error"));

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}