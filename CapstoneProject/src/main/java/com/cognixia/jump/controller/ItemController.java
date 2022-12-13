package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	ItemRepository repo;
	
	
}
