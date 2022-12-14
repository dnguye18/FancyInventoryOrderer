package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.repository.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	OrdersRepository repo;
	
	public List<Orders> getAllOrders() {
		return repo.findAll();
	}
	
	public Orders getOrderById(int id) throws ResourceNotFoundException {
		Optional<Orders> found = repo.findById(id);
		
		if( !found.isPresent() ) {
			throw new ResourceNotFoundException("Order", id);
		}
		
		return found.get();
	}
	
	public Orders addOrder(Orders order) {
		order.setId(null);
		Orders created = repo.save( order );
		return created;
	}
	
	public Orders updateOrder(Orders order) throws ResourceNotFoundException {
		if( !repo.existsById( order.getId() ) ) {
			throw new ResourceNotFoundException("Order", order.getId());
		}
		
		for(Item i : order.getItems()) {
			i.setOrder(order);
		}
		
		Orders updated = repo.save( order );
		
		return updated;
	}
	
	public Orders deleteOrder(int id) throws ResourceNotFoundException {
		Orders toDelete = getOrderById(id);
		repo.deleteById(id);
		return toDelete;
	}
}
