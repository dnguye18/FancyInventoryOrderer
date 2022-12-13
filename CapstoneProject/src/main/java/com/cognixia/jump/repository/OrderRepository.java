package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
