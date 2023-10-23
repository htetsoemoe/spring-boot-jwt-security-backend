package com.ninja.security.authService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ninja.security.config.JwtService;
import com.ninja.security.dto.AuthenticationResponse;
import com.ninja.security.dto.RegisterRequest;
import com.ninja.security.dto.ResponseUser;
import com.ninja.security.entity.Role;
import com.ninja.security.entity.User;
import com.ninja.security.repository.TokenRepository;
import com.ninja.security.repository.UserRepository;
import com.ninja.security.token.Token;
import com.ninja.security.token.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	
	public AuthenticationResponse register(RegisterRequest request) {
		// Create Registered User
		var registeredUser = User.builder()
				.firstName(request.getFirstname())
				.lastName(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ADMIN)
				.build();
		
		// Save registered user to database
		var savedUser = userRepository.save(registeredUser);
		
		// Generate JWT Token using registered user(userDetails)
		String jwt = jwtService.generateToken(registeredUser);
		
		// Generate JWT Refresh Token using registered user(userDetails)
		String refreshToken = jwtService.generateRefreshToken(registeredUser);
		
		// Save Access Token
		saveUserToken(savedUser, jwt);
		
		ResponseUser responseUser = ResponseUser.builder()
				.firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName())
				.email(savedUser.getEmail())
				.role(savedUser.getRole().toString())
				.build();
		
		return AuthenticationResponse.builder()
				.user(responseUser)
				.httpStatus(HttpStatus.Series.SUCCESSFUL.toString().toLowerCase())
				.accessToken(jwt)
				.refreshToken(refreshToken)
				.build();
	}
	
	// Save user and generated token
	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();
		tokenRepository.save(token);
	}
}
