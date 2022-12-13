package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;

public interface ItemRepository extends JpaRepository<Item	,Integer> {

	//@Query("SELECT i FROM Item i WHERE i.order_id = ?1")
	//public List<Item> sameOrder(Orders order);
}
