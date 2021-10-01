package com.cts.capstone.advice;

import com.cts.capstone.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerNotFoundAdviceTest {

	CustomerNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new CustomerNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new CustomerNotFoundException());
		assertEquals("Error 404: Could not find customer", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new CustomerNotFoundException("id123"));
		assertEquals("Error 404: Could not find customer id123", actual);
	}

}