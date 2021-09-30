package com.cts.capstone.advice;

import com.cts.capstone.exception.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SupplierNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(SupplierNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String notFoundHandler(SupplierNotFoundException ex) {
		return "Error 404: " + ex.getMessage();
	}
}