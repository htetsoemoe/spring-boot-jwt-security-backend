package com.ninja.security.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
	
	USER_CREATE("user:create"),
	USER_READ("user:read"),
	USER_UPDATE("user:update"),
	USER_DELETE("user:delete"),
	
	ADMIN_CREATE("admin:create"),
	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_DELETE("admin:delete");
	
	@Getter
	private final String permission;

}
