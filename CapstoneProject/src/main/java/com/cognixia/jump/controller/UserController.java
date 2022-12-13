package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

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
import com.cognixia.jump.model.Orders;
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
	
	@GetMapping("/user/get/{id}")
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
		
		for(Orders o : user.getOrders() ) {
			o.setId(null);
			o.setUsr(user);
		}
	
		User created = repo.save( user );
		
		return ResponseEntity.status(HttpStatus.CREATED).body( created );
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Orders order) throws ResourceNotFoundException {
		Optional<User> user = repo.findById(id);
		if( user.isPresent() ) {
			
			User updated = user.get();
			updated.getOrders().add(order);
			order.setUsr(updated);
			User finished = repo.save(updated);
			
			System.out.println(finished.getOrders());
			System.out.println(order.getUsr().toString());
			
			
			return ResponseEntity.status(HttpStatus.OK).body( finished );
		}
		
		throw new ResourceNotFoundException("User", id);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
		if( repo.existsById(id) ) {
			User toBeDeleted = (User)getUserById(id).getBody();
			
			repo.deleteById(id);
			
			return ResponseEntity.status(HttpStatus.OK).body( toBeDeleted );
		}
		
		throw new ResourceNotFoundException("User", id);
	}
}
