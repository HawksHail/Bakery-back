package com.cts.capstone.advice;

import com.cts.capstone.exception.ExceptionResponse;
import com.cts.capstone.exception.OrderDetailsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
class OrderDetailsResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OrderDetailsNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(OrderDetailsNotFoundException ex, final HttpServletRequest httpServletRequest) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), httpServletRequest.getRequestURI());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}