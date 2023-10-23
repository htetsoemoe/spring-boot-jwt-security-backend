package com.ninja.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ninja.security.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	@JsonProperty("user")
	private ResponseUser user;
	
	@JsonProperty("status")
	private String httpStatus;
	
	@JsonProperty("token")
	private String accessToken;
	
	@JsonProperty("refresh_token")
	private String refreshToken;

}
