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
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.repository.OrdersRepository;
import com.cognixia.jump.service.OrdersService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	OrdersService service;
	
	@GetMapping("/orders")
	public List<Orders> getAllOrders() {
		return service.getAllOrders();
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable int id) throws ResourceNotFoundException {
		Orders found = service.getOrderById(id);
		return ResponseEntity.status(HttpStatus.OK).body( found );
	}
	
	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Orders order) {
		Orders created = service.addOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body( created );
	}
	
	@PutMapping("/orders")
	public ResponseEntity<?> updateOrder(@Valid @RequestBody Orders order) throws ResourceNotFoundException {
		Orders updated = service.updateOrder(order);
		return ResponseEntity.status(HttpStatus.OK).body( updated );
	}	
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable int id) throws ResourceNotFoundException {
		Orders deleted = service.deleteOrder(id);
		return ResponseEntity.status(HttpStatus.OK).body( deleted );
	}
	
	@PutMapping("/orders/{id}/item/add")
	public ResponseEntity<?> updateOrderAddItem(@PathVariable int id, @Valid@RequestBody Item item) throws ResourceNotFoundException {
		Orders updated = service.updateOrderAddItem(id, item);
		return ResponseEntity.status(HttpStatus.OK).body( updated );
	}
}
