package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@Controller
@SessionAttributes("username")
public class TodoController {
	@Autowired
	TodoService todoService;
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String getAllTodos(ModelMap model) {
		String username = (String) model.get("username");
		List<Todo> todos = todoService.retrieveTodos(username);
		model.put("todoNames", todos.toString());
		return "todoList";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showAddTodoPage() {
		return "addTodo";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addTodo(@RequestParam String desc, ModelMap model) {
		String username = (String) model.get("username");
		todoService.addTodo(username, desc, new Date(), false);
		return "redirect:/list-todos";
	}
}
