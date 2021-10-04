package com.cts.capstone.advice;

import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.ProductNotFoundException;
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

class ProductResponseEntityExceptionHandlerTest {

	ProductResponseEntityExceptionHandler notFoundAdvice;

	@Mock
	HttpServletRequest httpServletRequest;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		notFoundAdvice = new ProductResponseEntityExceptionHandler();

		when(httpServletRequest.getRequestURI()).thenReturn("URL");
	}

	@Test
	void handleNotFoundException() {
		ProductNotFoundException exception = new ProductNotFoundException(1);
		ResponseEntity<ExceptionResponse> response = notFoundAdvice.handleNotFoundException(exception, httpServletRequest);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Not Found", Objects.requireNonNull(response.getBody()).getError());
	}

}