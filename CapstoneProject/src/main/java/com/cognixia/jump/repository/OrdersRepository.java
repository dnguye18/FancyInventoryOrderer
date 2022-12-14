package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Orders.Progress;
import com.cognixia.jump.model.User;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	public List<Orders> findByProgress(Progress progress);
}
