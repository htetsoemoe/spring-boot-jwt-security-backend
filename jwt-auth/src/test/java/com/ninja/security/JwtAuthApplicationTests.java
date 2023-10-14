package com.ninja.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ninja.security.entity.Permission;

@SpringBootTest
class JwtAuthApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void test() {
		System.out.println(Permission.ADMIN_CREATE.getPermission() // admin:create
				+ " " + Permission.ADMIN_CREATE.name()); // ADMIN_CREATE
	}

}
