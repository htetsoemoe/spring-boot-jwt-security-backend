package com.ninja.security.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * This component was used in 'SecurityConfiguration File' 
 * as exceptionHandling (Authentication Failure Exception).
 */

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		logger.error("Unauthorized Error: {}", authException.getMessage());
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		// Response Body
		final Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		responseBody.put("error", "Unauthorized");
		responseBody.put("message", authException.getMessage());
		responseBody.put("path", request.getServletPath());
		
		final ObjectMapper mapper = new ObjectMapper();
		//Method that can be used to serialize any Java value as JSON output, using output stream provided (using encoding JsonEncoding.UTF8).
		mapper.writeValue(response.getOutputStream(), responseBody);
	}

}
