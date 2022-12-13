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
import com.cognixia.jump.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	ItemRepository repo;
	
	@GetMapping("/item")
	public List<Item> getAllItems() {
		return repo.findAll();
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<?> getItemById(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Item> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("Item", id);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(found.get());
	}
	
	@PostMapping("/item")
	public ResponseEntity<?> createItem(@Valid @RequestBody Item item) {
		item.setId(null);
		
		Item created = repo.save( item );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	// TODO PUT MAPPING UPDATE ITEM
	@PutMapping("/item")
	public ResponseEntity<?> updateItem(@Valid @RequestBody Item item) throws ResourceNotFoundException {
		if( repo.existsById( item.getId() ) ) {
			
			return ResponseEntity.status(HttpStatus.OK).body( item );
		}
		
		throw new ResourceNotFoundException("Item", item.getId());
	}
	
	// TODO
}
