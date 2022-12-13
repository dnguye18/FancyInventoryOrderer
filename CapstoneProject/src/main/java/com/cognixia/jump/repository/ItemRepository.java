package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.Item;

public interface ItemRepository extends JpaRepository<Item	,Integer> {

}
