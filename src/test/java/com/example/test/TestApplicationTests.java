package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.test.user.UserService;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void testJpa() {
		String username = "admin";
		String password = "12345";
		this.userService.create(username, password);
	}

}
