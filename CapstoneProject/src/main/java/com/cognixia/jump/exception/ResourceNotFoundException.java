package com.cognixia.jump.exception;

import com.cognixia.jump.model.User;

public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resource, int id) {
		super(resource + " was not found with id = " + id);
	}
}
