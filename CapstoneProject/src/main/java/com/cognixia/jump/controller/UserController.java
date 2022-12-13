package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository repo;
	
	
	// CRUD METHODS
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) throws ResourceNotFoundException {
		Optional<User> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("User", id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body( found.get() );
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		user.setId(null);
		
		// TODO --> IMPLEMENT PASSWORD ENCODER
		//user.setPassword( encoder.encode( user.getPassword() ) );
	
		User created = repo.save( user );
		
		return ResponseEntity.status(HttpStatus.CREATED).body( created );
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
		if( repo.existsById( user.getId() ) ) {
			for( Order order : user.getOrders() ) {
				order.setUser(user);
			}
			
			User updated = repo.save( user );
			
			return ResponseEntity.status(HttpStatus.OK).body( updated );
		}
		
		throw new ResourceNotFoundException("User", user.getId());
	}
}
