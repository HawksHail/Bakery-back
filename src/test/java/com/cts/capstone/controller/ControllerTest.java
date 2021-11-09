package com.cts.capstone.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

	Controller controller;

	@BeforeEach
	void setup() {
		controller = new Controller();
	}

	@Test
	void ok() {
		ResponseEntity<Object> actual = controller.ok();

		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertEquals("Ok", actual.getBody());
	}
}