package com.cts.capstone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping
	public ResponseEntity<Object> ok(){
		return ResponseEntity.ok().build();
	}
}
