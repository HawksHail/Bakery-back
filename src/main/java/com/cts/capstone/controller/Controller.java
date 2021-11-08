package com.cts.capstone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
	@GetMapping
	public ResponseEntity<Object> ok() {
		return ResponseEntity.ok("Ok");
	}

	@GetMapping("authz")
	@ResponseBody
	public List<String> getAuthz(Authentication authentication) {
		return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}
}
