package com.cognixia.jump.exception;

import com.cognixia.jump.model.User;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(User user, int id) {
		super("User " + user.getEmail() + " was not found with id = " + id);
	}
}
