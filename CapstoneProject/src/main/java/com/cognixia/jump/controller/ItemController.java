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
import com.cognixia.jump.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	ItemService service;
	
	@GetMapping("/item")
	public List<Item> getAllItems() {
		return service.getAllItems();
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<?> getItemById(@PathVariable int id) throws ResourceNotFoundException {
		Item found = service.getItemById(id);
		return ResponseEntity.status(HttpStatus.OK).body( found );
	}
	
	@PostMapping("/item")
	public ResponseEntity<?> createItem(@Valid @RequestBody Item item) {
		Item created = service.addItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).body( created );
	}
	
	@PutMapping("/item")
	public ResponseEntity<?> updateItem(@Valid @RequestBody Item item) throws ResourceNotFoundException {
		Item updated = service.updateItem(item);
		return ResponseEntity.status(HttpStatus.OK).body( updated );
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable int id) throws ResourceNotFoundException {
		Item deleted = service.deleteItem(id);
		return ResponseEntity.status(HttpStatus.OK).body( deleted );
	}
}
