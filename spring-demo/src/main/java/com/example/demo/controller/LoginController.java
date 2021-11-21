package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.service.LoginService;
import com.example.demo.service.TodoService;

@Controller
@SessionAttributes("username")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getFormData(@RequestParam String username, @RequestParam String password, ModelMap model) {
		boolean validUser = loginService.isValidUser(username, password);
		
		if (!validUser) {
			model.put("invalidCredentials", "Invalid username or password");
			return "login";
		}
		
		model.put("username", username);
		model.put("password", password);
		return "welcome";
	}
	
}
