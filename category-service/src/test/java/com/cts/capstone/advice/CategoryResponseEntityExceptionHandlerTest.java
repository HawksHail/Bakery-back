package com.cts.capstone.advice;

import com.cts.capstone.exception.CategoryNotFoundException;
import com.cts.capstone.exception.ExceptionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CategoryResponseEntityExceptionHandlerTest {

	CategoryResponseEntityExceptionHandler notFoundAdvice;

	@Mock
	HttpServletRequest httpServletRequest;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		notFoundAdvice = new CategoryResponseEntityExceptionHandler();

		when(httpServletRequest.getRequestURI()).thenReturn("URL");
	}

	@Test
	void handleNotFoundException() {
		CategoryNotFoundException exception = new CategoryNotFoundException(1);
		ResponseEntity<ExceptionResponse> response = notFoundAdvice.handleNotFoundException(exception, httpServletRequest);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Not Found", Objects.requireNonNull(response.getBody()).getError());
	}

}