package com.cts.capstone.advice;

import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.OrderNotFoundException;
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

class OrderResponseEntityExceptionHandlerTest {

	OrderResponseEntityExceptionHandler notFoundAdvice;

	@Mock
	HttpServletRequest httpServletRequest;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		notFoundAdvice = new OrderResponseEntityExceptionHandler();

		when(httpServletRequest.getRequestURI()).thenReturn("URL");
	}

	@Test
	void handleNotFoundException() {
		OrderNotFoundException exception = new OrderNotFoundException(1);
		ResponseEntity<ExceptionResponse> response = notFoundAdvice.handleNotFoundException(exception, httpServletRequest);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Not Found", Objects.requireNonNull(response.getBody()).getError());
	}

}