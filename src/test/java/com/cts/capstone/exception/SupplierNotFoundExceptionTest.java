package com.cts.capstone.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SupplierNotFoundExceptionTest {

	private final static String message = "Could not find supplier";
	SupplierNotFoundException ex;

	@Test
	void def() {
		ex = new SupplierNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new SupplierNotFoundException(123L);

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new SupplierNotFoundException(123L, new Exception("error"));

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}