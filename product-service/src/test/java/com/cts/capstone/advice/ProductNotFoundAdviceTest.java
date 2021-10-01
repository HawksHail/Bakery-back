package com.cts.capstone.advice;

import com.cts.capstone.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductNotFoundAdviceTest {

	ProductNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new ProductNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new ProductNotFoundException());
		assertEquals("Error 404: Could not find product", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new ProductNotFoundException(1234));
		assertEquals("Error 404: Could not find product 1234", actual);
	}

}