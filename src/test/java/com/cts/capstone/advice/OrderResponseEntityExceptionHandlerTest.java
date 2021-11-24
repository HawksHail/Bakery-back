package com.cts.capstone.advice;

import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderResponseEntityExceptionHandlerTest {

	OrderResponseEntityExceptionHandler notFoundAdvice;

	ServletWebRequest servletWebRequest;

	@Mock
	HttpServletRequest httpServletRequest;

	@Mock
	BindingResult bindingResult;

	@Mock
	MethodParameter parameter;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		notFoundAdvice = new OrderResponseEntityExceptionHandler();

		servletWebRequest = new ServletWebRequest(httpServletRequest);
		when(httpServletRequest.getRequestURI()).thenReturn("URL");
	}

	@Test
	void handleNotFoundException() {
		OrderNotFoundException exception = new OrderNotFoundException(1L);
		ResponseEntity<ExceptionResponse> response = notFoundAdvice.handleNotFoundException(exception, servletWebRequest);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Not Found", Objects.requireNonNull(response.getBody()).getError());
	}

	@Test
	void handleMethodArgumentNotValid() {
		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);

		ResponseEntity<Object> response = notFoundAdvice.handleMethodArgumentNotValid(exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, servletWebRequest);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Validation Failed", ((ExceptionResponse) Objects.requireNonNull(response.getBody())).getError());
	}

}