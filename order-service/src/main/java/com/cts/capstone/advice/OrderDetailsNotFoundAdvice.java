package com.cts.capstone.advice;

import com.cts.capstone.exception.OrderDetailsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OrderDetailsNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(OrderDetailsNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String notFoundHandler(OrderDetailsNotFoundException ex) {
		return "Error 404: " + ex.getMessage();
	}
}