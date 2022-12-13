package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	public User getUserById(int id) throws ResourceNotFoundException {
		Optional<User> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("User", id);
		}
		
		return found.get();
	}
	
	public User addUser(User user) {
		user.setId(null);
		user.setPassword( encoder.encode( user.getPassword() ) );
		
		for(Orders o : user.getOrders() ) {
			o.setId(null);
			o.setUsr(user);
		}
		
		User saved = repo.save( user );
		return saved;
	}
	
	public User updateUser(User user) throws ResourceNotFoundException {
		if( !repo.existsById(user.getId()) ) {
			throw new ResourceNotFoundException("User", user.getId());
		}
		
		for( Orders order : user.getOrders() ) {
			order.setUsr(user);
		}
		
		User updated = repo.save(user);
		
		return updated;
	}
	
	public User deleteUser(int id) throws ResourceNotFoundException {
		User toDelete = getUserById(id);
		repo.deleteById(id);
		return toDelete;
	}
}
