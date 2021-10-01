package com.cts.capstone.advice;

import com.cts.capstone.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryNotFoundAdviceTest {

	CategoryNotFoundAdvice notFoundAdvice;

	@BeforeEach
	void setup() {
		notFoundAdvice = new CategoryNotFoundAdvice();
	}

	@Test
	void notFoundHandler() {
		String actual = notFoundAdvice.notFoundHandler(new CategoryNotFoundException());
		assertEquals("Error 404: Could not find category", actual);
	}

	@Test
	void notFoundHandlerId() {
		String actual = notFoundAdvice.notFoundHandler(new CategoryNotFoundException(1234));
		assertEquals("Error 404: Could not find category 1234", actual);
	}

}