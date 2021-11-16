package com.cts.capstone.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerNotFoundExceptionTest {

	private final static String message = "Could not find customer";
	CustomerNotFoundException ex;

	@Test
	void def() {
		ex = new CustomerNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new CustomerNotFoundException(123L);

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new CustomerNotFoundException(123L, new Exception("error"));

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}