package com.in28minutes.learnspringsecurity.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {
	
	@PostMapping(path="/authentication")
	public Authentication authenticate(Authentication authentication) {
		return authentication;
	}
}
