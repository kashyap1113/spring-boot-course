package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Todo {
	private int id;
	private String user;
	private String description;
	private Date targetDate;
	private boolean isDone;
	
	public Todo(int id, String user, String description, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Todo)) {
			return false;
		}
		Todo other = (Todo) obj;
		return id == other.id;
	}
}
