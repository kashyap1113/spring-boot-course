<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>
<h3>Your Todos</h3>
<br>
<table>
    <caption>Here is list of your Todos</caption>
    <thead>
        <tr>
	        <th>Description</th>
	        <th>Date</th>
	        <th>Is Done</th>
        </tr>
    </thead>
    
    <tbody>
        <c:forEach items="${todos}" var="todo">
	        <tr>
	            <td>${todo.description}</td>
	            <td>${todo.targetDate}</td>
	            <td>${todo.done}</td>
	        </tr>
        </c:forEach>
    </tbody>
</table>
<br>
<a href="/add-todo">Click Here</a> to add new Todo.
</body>
</html>