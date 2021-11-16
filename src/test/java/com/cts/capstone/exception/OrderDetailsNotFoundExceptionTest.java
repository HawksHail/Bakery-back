package com.cts.capstone.exception;

import com.cts.capstone.model.OrderDetailsKey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderDetailsNotFoundExceptionTest {

	private final static String message = "Could not find order details";
	OrderDetailsNotFoundException ex;

	@Test
	void def() {
		ex = new OrderDetailsNotFoundException();

		Assertions.assertEquals(message, ex.getMessage());
	}

	@Test
	void id() {
		ex = new OrderDetailsNotFoundException(new OrderDetailsKey(123L, 456L));

		Assertions.assertEquals(message + " " + 123L + " " + 456L, ex.getMessage());
	}

	@Test
	void cause() {
		ex = new OrderDetailsNotFoundException(new OrderDetailsKey(123L, 456L), new Exception("error"));

		Assertions.assertEquals(message + " " + 123L + " " + 456L, ex.getMessage());
		Assertions.assertEquals("error", ex.getCause().getMessage());
	}
}