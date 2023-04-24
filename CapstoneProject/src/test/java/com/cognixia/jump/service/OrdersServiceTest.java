package com.cognixia.jump.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Item;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Orders.Progress;
import com.cognixia.jump.repository.OrdersRepository;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {
	@Mock
	private OrdersRepository repo;
	
	@InjectMocks
	private OrdersService service;
	
	@Test
	void testGetAllOrders() throws Exception {
		List<Orders> allOrders = new ArrayList<Orders>();
		allOrders.add(new Orders(1, Progress.COMPLETED, null));
		allOrders.add(new Orders(2, Progress.IN_PROGRESS, null));
		
		when( repo.findAll() ).thenReturn(allOrders);
		
		List<Orders> result = service.getAllOrders();
		
		for(int i = 0; i < allOrders.size(); i++) {
			if( !allOrders.get(i).equals( result.get(i) ) ) {
				fail();
			}
		}
	}
	
	@Test
	void testGetOrderById() throws Exception {
		int id = 1;
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( repo.findById(id) ).thenReturn( Optional.of(order) );
		
		Orders result = service.getOrderById(id);
		
		assertEquals(order, result);
	}
	
	@Test
	void testGetOrderByIdNotFound() throws Exception {
		int id = 1;
		
		when( repo.findById(id) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.getOrderById(id);
		} );
	}
	
	@Test
	void testAddOrder() throws Exception {
		Orders order = new Orders(1, Progress.COMPLETED, null);
		when( repo.save( Mockito.any(Orders.class) ) ).thenReturn( order );
		Orders result = service.addOrder( order );
		
		assertEquals(order, result);
	}
	
	@Test
	void testUpdateOrder() throws Exception {
		int id = 1;
		Orders order = new Orders(1, Progress.COMPLETED, null);
		order.setItems(new ArrayList<Item>());
		
		when( repo.existsById(id) ).thenReturn( true );
		when( repo.save( Mockito.any(Orders.class) ) ).thenReturn(order);
	
		Orders result = service.updateOrder(order);
		
		assertEquals(order, result);
	}
	
	@Test
	void testUpdateOrderNotFound() {
		int id = 1;
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( repo.existsById(id) ).thenReturn(false);
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.updateOrder(order);
		} );
	}
	
	@Test
	void testDeleteOrder() throws Exception {
		int id = 1;
		Orders order = new Orders(1, Progress.COMPLETED, null);
		
		when( repo.findById(id) ).thenReturn( Optional.of( order ) );
		service.deleteOrder( id );
		
		verify( repo, times(1) ).deleteById(id);
		verifyNoMoreInteractions( repo );
	}
	
	@Test
	void testDeleteOrderNotFound() throws Exception {
		int id = 1;
	
		when( repo.findById(id) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.deleteOrder(id);
		} );
	}
}
