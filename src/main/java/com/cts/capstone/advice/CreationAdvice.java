package com.cts.capstone.advice;

import com.cts.capstone.exception.CreationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CreationAdvice {

	@ResponseBody
	@ExceptionHandler(CreationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String creationHandler(CreationException ex) {
		return "Error 409: " + ex.getMessage();
	}
}
