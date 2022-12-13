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
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {
	@Autowired
	OrderRepository repo;
	
	@GetMapping("/order")
	public List<Orders> getAllOrders() {
		return repo.findAll();
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Orders> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("Order", id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(found);
	}
	
	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Orders order) {
		order.setId(null);
		
		Orders created = repo.save(order);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping("/order")
	public ResponseEntity<?> updateCustomer(@Valid @RequestBody Orders order) throws ResourceNotFoundException {
		if( repo.existsById( order.getId() ) ) {
			for(Item i : order.getItems()) {
				i.setOrder(order);
			}
			
			Orders updated = repo.save( order );
			
			return ResponseEntity.status(HttpStatus.OK).body( updated );
		}
		
		throw new ResourceNotFoundException("Order", order.getId());
	}	
}
