package com.cts.capstone.advice;

import com.cts.capstone.exception.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderNotFoundAdviceTest {

	OrderNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new OrderNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new OrderNotFoundException());
		assertEquals("Error 404: Could not find order", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new OrderNotFoundException(1234));
		assertEquals("Error 404: Could not find order 1234", actual);
	}

}