package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoId = 0;
	
	static {
		todos.add(new Todo(++todoId, "user1", "desc1", new Date(), false));
		todos.add(new Todo(++todoId, "user1", "desc2", new Date(), false));
		todos.add(new Todo(++todoId, "user1", "desc3", new Date(), false));
		todos.add(new Todo(++todoId, "user2", "desc1", new Date(), false));
		todos.add(new Todo(++todoId, "user2", "desc2", new Date(), false));
	}
	
	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoId, name, desc, targetDate, isDone));
	}
	
	public void deleteTodo(int id) {
		Iterator<Todo> todoIterator = todos.iterator();
		
		while (todoIterator.hasNext()) {
			Todo todo = todoIterator.next();
			if (id == todo.getId()) {
				todoIterator.remove();
			}
		}
	}
	
	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<>();
		
		for (Todo todo : todos) {
			if (todo.getUser().equalsIgnoreCase(user)) {
				filteredTodos.add(todo);
			}
		}
		
		return filteredTodos;
	}
}
