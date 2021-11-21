package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyLoginController {
	
	@RequestMapping(value = "/myLogin")
	public String performLogin(@RequestParam String username, @RequestParam String password, @RequestParam int otp, ModelMap model) {
		System.err.println("======= username = " + username);
		System.err.println("======= username = " + password);
		System.err.println("======= username = " + (otp + 1));
		model.put("username", username);
		model.put("password", password);
		model.put("otp", otp);
		
		return "myLoginPage";
	}
}
