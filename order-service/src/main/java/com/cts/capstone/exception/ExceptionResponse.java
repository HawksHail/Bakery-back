package com.cts.capstone.exception;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {

	private LocalDateTime timestamp;
	private HttpStatus status;
	private String error;
	private String message;
	private String path;

	public ExceptionResponse(LocalDateTime timestamp, HttpStatus status, String error, String message, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public static ExceptionResponse of(HttpStatus status, String error, String message, String path) {
		return new ExceptionResponse(LocalDateTime.now(), status, error, message, path);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status.getReasonPhrase();
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@JsonIgnore
	public HttpStatus getStatusObj() {
		return status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
