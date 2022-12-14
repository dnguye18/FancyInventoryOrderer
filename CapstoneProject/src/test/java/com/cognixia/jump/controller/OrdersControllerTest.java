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
import com.cognixia.jump.service.OrdersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrdersControllerTest {
	private static final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private OrdersService service;
	
	@InjectMocks
	private OrderController controller;
	
	@Test
	void testGetAllOrders() throws Exception {
		String uri = STARTING_URI + "/orders";
		List<Orders> allOrders = new ArrayList<Orders>();
		allOrders.add(new Orders(1, Progress.COMPLETED, null));
		allOrders.add(new Orders(2, Progress.IN_PROGRESS, null));
		
		when( service.getAllOrders() ).thenReturn( allOrders );
		
		mvc.perform( get(uri) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath("$.length()").value( allOrders.size() ) )
			.andExpect( jsonPath("$[0].id").value( allOrders.get(0).getId() ) )
			.andExpect( jsonPath("$[0].progress").value( allOrders.get(0).getProgress() ) )
			.andExpect( jsonPath("$[0].usr").value( allOrders.get(0).getUsr() ) )
			.andExpect( jsonPath("$[1].id").value( allOrders.get(1).getId() ) )
			.andExpect( jsonPath("$[1].progress").value( allOrders.get(1).getProgress() ) )
			.andExpect( jsonPath("$[1].usr").value( allOrders.get(1).getUsr() ) )
		;
		
		verify( service, times(1) ).getAllOrders();
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void getOrderById() throws Exception {
		String uri = STARTING_URI + "/orders/{id}";
		int id = 1;
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( service.getOrderById( id ) ).thenReturn( order );
		
		mvc.perform( get(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath("$.id").value( order.getId() ) )
			.andExpect( jsonPath("$.progress").value( order.getProgress() ) )
			.andExpect( jsonPath("$.usr").value( order.getUsr() ) )
		;
		
		verify( service, times(1) ).getOrderById(id);
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void testGetOrderByIdNotFound() throws Exception {
		String uri = STARTING_URI + "/orders/{id}";
		int id = 1;
		
		when( service.getOrderById( id ) )
			.thenThrow( new ResourceNotFoundException("Order", id) )
		;
		
		mvc.perform( get(uri, id) ) 
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testCreateOrder() throws Exception {
		String uri = STARTING_URI + "/orders";
		Orders order = new Orders(1, Progress.COMPLETED, null);
		order.setItems( new ArrayList<Item>() );
		
		when( service.addOrder( Mockito.any(Orders.class) ))
			.thenReturn( order );
		
		mvc.perform( post(uri) 
					.content( asJsonString(order) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isCreated() )
		;
	}
	
	@Test
	void testUpdateOrder() throws Exception {
		String uri = STARTING_URI + "/orders";
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( service.updateOrder( Mockito.any(Orders.class) ) )
			.thenReturn( order );
		
		mvc.perform( put(uri) 
					.content( asJsonString(order) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testUpdateOrderNotFound() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/orders";
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( service.updateOrder( Mockito.any(Orders.class) ) )
			.thenThrow( new ResourceNotFoundException("order", id) );
		
		mvc.perform( put(uri)
					.content( asJsonString(order) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
				)
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testDeleteOrder() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/orders/{id}";
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( service.deleteOrder(id) ).thenReturn(order);
		
		mvc.perform( delete(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testDeleteOrderNotFound() throws Exception {
		int id = 1;
		String uri = STARTING_URI + "/orders/{id}";
		
		when( service.deleteOrder(id) )
			.thenThrow( new ResourceNotFoundException("Order", id) )
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
