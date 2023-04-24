package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService service;
	
	// CRUD METHODS
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) throws ResourceNotFoundException {
		User found = service.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body( found );
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		User created = service.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body( created );
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws ResourceNotFoundException {
		User updated = service.updateUser(user);
		return ResponseEntity.status(HttpStatus.OK).body( updated );
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
		User deleted = service.deleteUser(id);
		return ResponseEntity.status(HttpStatus.OK).body( deleted );
	}
	
	@GetMapping("/user/phone/{phone}")
	public ResponseEntity<?> getUserByPhone(@PathVariable String phone) throws ResourceNotFoundException {
		User found = service.getUserByPhone(phone);
		return ResponseEntity.status(HttpStatus.OK).body( found );
	}
	
	@GetMapping("/user/username/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws ResourceNotFoundException {
		User found = service.getUserByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body( found );
	}
	
	@PutMapping("/user/{id}/item/add")
	public ResponseEntity<?> updateUserAddItem(@PathVariable int id, @Valid @RequestBody Item item) throws ResourceNotFoundException {
		User updated = service.updateUserAddItem(id, item);
		return ResponseEntity.status(HttpStatus.OK).body( updated );
	}
}
