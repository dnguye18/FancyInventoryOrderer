package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Item;
import com.cognixia.jump.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository repo;
	
	public List<Item> getAllItems() {
		return repo.findAll();
	}
	
	public Item getItemById(int id) throws ResourceNotFoundException {
		Optional<Item> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("Item", id);
		}
		
		return found.get();
	}
	
	public Item addItem(Item item) {
		item.setId(null);
		Item saved = repo.save( item );
		return saved;
	}
	
	public Item updateItem(Item item) throws ResourceNotFoundException {
		if( !repo.existsById( item.getId() ) ) {
			throw new ResourceNotFoundException("Item", item.getId());
		}
		
		Item updated = repo.save(item);
		
		return updated;
	}
	
	public Item deleteItem(int id) throws ResourceNotFoundException {
		Item toDelete = getItemById(id);
		repo.deleteById(id);
		return toDelete;
	}
	
	public List<Item> getItemsWithMaxQuantity(int qty) {
		return repo.itemsWithMaxQty(qty);
	}
}
