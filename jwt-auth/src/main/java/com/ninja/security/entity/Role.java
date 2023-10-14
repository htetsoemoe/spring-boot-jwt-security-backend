package com.ninja.security.entity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	USER(
			Set.of(
					Permission.USER_CREATE,
					Permission.USER_READ,
					Permission.USER_UPDATE,
					Permission.USER_DELETE
			)
	),
	ADMIN(
			Set.of(
					Permission.ADMIN_CREATE,
					Permission.ADMIN_READ,
					Permission.ADMIN_UPDATE,
					Permission.ADMIN_DELETE,
					Permission.USER_CREATE,
					Permission.USER_READ,
					Permission.USER_UPDATE,
					Permission.USER_DELETE
			)
	);
	
	@Getter
	private final Set<Permission> permissions;
	
	public List<SimpleGrantedAuthority> getAuthorities() {
		var authorities = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())) // getPermission => admin:create
				.collect(Collectors.toList());
		
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name())); // e.g, ROLE_USER
		
		// return [user:create, user:read, user:update, user:delete, ROLE_USER]
		return authorities;
	}
}
