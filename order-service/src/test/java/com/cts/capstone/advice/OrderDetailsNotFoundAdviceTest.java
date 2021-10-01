package com.cts.capstone.advice;

import com.cts.capstone.exception.OrderDetailsNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDetailsNotFoundAdviceTest {

	OrderDetailsNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new OrderDetailsNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new OrderDetailsNotFoundException());
		assertEquals("Error 404: Could not find order details", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new OrderDetailsNotFoundException(1234));
		assertEquals("Error 404: Could not find order details 1234", actual);
	}

}