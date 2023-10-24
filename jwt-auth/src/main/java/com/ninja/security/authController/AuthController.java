package com.ninja.security.authController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.security.authService.AuthenticationService;
import com.ninja.security.dto.AuthenticationRequest;
import com.ninja.security.dto.AuthenticationResponse;
import com.ninja.security.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {	
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authenticationService.register(request));
	}
	
	@PostMapping("login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.login(request));
	}

}
