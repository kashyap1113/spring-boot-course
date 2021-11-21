package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Todo;
import com.example.demo.service.LoginService;
import com.example.demo.service.TodoService;

@Controller
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
	
	@RequestMapping(value = "/todo-list")
	public String getAllTodos(@RequestParam String username, ModelMap model) {
		List<Todo> todos = todoService.retrieveTodos(username);
		
		String todoNames = "";
		for (Todo todo : todos) {
			todoNames += todo.getDescription() + ",";
		}
		
		model.put("todoNames", todoNames);
		return "todoList";
	}
}
