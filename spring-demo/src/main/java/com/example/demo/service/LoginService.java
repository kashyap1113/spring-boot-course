package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean isValidUser(String username, String password) {
		return username.equalsIgnoreCase("user1") 
				&& password.equals("111");
	}
}
