package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Orders.Progress;
import com.cognixia.jump.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ItemController.class)
@ExtendWith(SpringExtension.class)
public class ItemControllerTest {
	private static final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ItemService service;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	void testGetAllItems() throws Exception {
		String uri = STARTING_URI + "/item";
		List<Item> allItems = new ArrayList<Item>();
		allItems.add(new Item(1, null, "banana", "0.99", 4));
		allItems.add(new Item(1, null, "orange", "0.50", 2));
	
		when( service.getAllItems() ).thenReturn( allItems );
		
		mvc.perform( get(uri) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath("$.length()").value( allItems.size() ) )
			.andExpect( jsonPath("$[0].id").value( allItems.get(0).getId() ) )
			.andExpect( jsonPath("$[0].order").value( allItems.get(0).getOrder() ) )
			.andExpect( jsonPath("$[0].name").value( allItems.get(0).getName() ) )
			.andExpect( jsonPath("$[0].price").value( allItems.get(0).getPrice() ) )
			.andExpect( jsonPath("$[0].qty").value( allItems.get(0).getQty() ) )
			.andExpect( jsonPath("$[1].id").value( allItems.get(1).getId() ) )
			.andExpect( jsonPath("$[1].order").value( allItems.get(1).getOrder() ) )
			.andExpect( jsonPath("$[1].name").value( allItems.get(1).getName() ) )
			.andExpect( jsonPath("$[1].price").value( allItems.get(1).getPrice() ) )
			.andExpect( jsonPath("$[1].qty").value( allItems.get(1).getQty() ) )
		;
	
		verify( service, times(1) ).getAllItems();
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void getItemById() throws Exception {
		String uri = STARTING_URI + "/item/{id}";
		int id = 1;
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( service.getItemById( id ) ).thenReturn( item );
		
		mvc.perform( get(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath("$.id").value( item.getId() ) )
			.andExpect( jsonPath("$.order").value( item.getOrder() ) )
			.andExpect( jsonPath("$.name").value( item.getName() ) )
			.andExpect( jsonPath("$.price").value( item.getPrice() ) )
			.andExpect( jsonPath("$.qty").value( item.getQty() ) )
		;
		
		verify( service, times(1) ).getItemById(id);
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void testGetItemByIdNotFound() throws Exception {
		String uri = STARTING_URI + "/orders/{id}";
		int id = 1;
		
		when( service.getItemById( id ) )
			.thenThrow( new ResourceNotFoundException("Item", id) )
		;
		
		mvc.perform( get(uri, id) ) 
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testAddItem() throws Exception {
		String uri = STARTING_URI + "/item";
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( service.addItem( Mockito.any(Item.class) ) )
			.thenReturn( item )
		;
		
		mvc.perform( post(uri)
					.content( asJsonString(item) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isCreated() )
		;
	}
	
	@Test
	void testUpdateItem() throws Exception {
		String uri = STARTING_URI + "/item";
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( service.updateItem( Mockito.any(Item.class) ) )
			.thenReturn( item );
		
		mvc.perform( put(uri) 
					.content( asJsonString(item) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testUpdateItemNotFound() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/item";
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( service.updateItem( Mockito.any(Item.class) ) )
			.thenThrow( new ResourceNotFoundException("Item", id) );
		
		mvc.perform( put(uri)
					.content( asJsonString(item) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testDeleteItem() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/item/{id}";
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( service.deleteItem(id) ).thenReturn(item);
		
		mvc.perform( delete(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testDeleteItemNotFound() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/item/{id}";
		
		when( service.deleteItem(id) )
			.thenThrow( new ResourceNotFoundException("Item", id) )
		;
	
		mvc.perform( delete(uri, id) )
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	public static String asJsonString(final Object obj) {
		
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch(JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
}
