package com.cts.capstone.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNull;

class ExceptionResponseTest {

	ExceptionResponse response;

	@BeforeEach
	void setUp() {
		response = new ExceptionResponse(LocalDateTime.now(), HttpStatus.OK, "error string", "message string", "path string");
	}

	@Test
	void setTimestamp() {
		response.setTimestamp(null);

		assertNull(response.getTimestamp());
	}

	@Test
	void setStatus() {
		response.setStatus(null);

		assertNull(response.getStatusObj());
	}

	@Test
	void setError() {
		response.setError(null);

		assertNull(response.getError());
	}

	@Test
	void setMessage() {
		response.setMessage(null);

		assertNull(response.getMessage());
	}

	@Test
	void setPath() {
		response.setPath(null);

		assertNull(response.getPath());
	}
}