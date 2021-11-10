package com.cts.capstone.advice;

import com.cts.capstone.exception.ExceptionResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
class DateIntegrityViolationResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(DataIntegrityViolationException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
				HttpStatus.BAD_REQUEST,
				"Data Integrity Violation",
				ex.getCause().getCause().getMessage(),
				((ServletWebRequest) request).getRequest().getRequestURI());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
				status,
				"Validation Failed",
				ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")),
				((ServletWebRequest) request).getRequest().getRequestURI());
		return handleExceptionInternal(ex, exceptionResponse, headers, exceptionResponse.getStatusObj(), request);
	}
}
