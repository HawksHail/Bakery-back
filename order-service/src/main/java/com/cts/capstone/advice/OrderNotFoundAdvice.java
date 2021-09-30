package com.cts.capstone.advice;

import com.cts.capstone.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class OrderNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String notFoundHandler(OrderNotFoundException ex) {
		return "Error 404: " + ex.getMessage();
	}
}