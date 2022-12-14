package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;

@Repository
public interface ItemRepository extends JpaRepository<Item	,Integer> {
	@Query("select i from Item i where i.qty < ?1")
	public List<Item> itemsWithMaxQty(int qty);
}
