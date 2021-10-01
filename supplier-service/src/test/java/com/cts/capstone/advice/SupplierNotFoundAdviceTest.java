package com.cts.capstone.advice;

import com.cts.capstone.exception.SupplierNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierNotFoundAdviceTest {

	SupplierNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new SupplierNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new SupplierNotFoundException());
		assertEquals("Error 404: Could not find supplier", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new SupplierNotFoundException(1234));
		assertEquals("Error 404: Could not find supplier 1234", actual);
	}

}