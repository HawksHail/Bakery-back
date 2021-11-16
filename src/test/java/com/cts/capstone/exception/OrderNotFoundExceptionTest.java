package com.cts.capstone.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderNotFoundExceptionTest {

	private final static String message = "Could not find order";
	OrderNotFoundException ex;

	@Test
	void def() {
		ex = new OrderNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new OrderNotFoundException(123L);

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new OrderNotFoundException(123L, new Exception("error"));

		Assertions.assertEquals(message + " " + 123L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}