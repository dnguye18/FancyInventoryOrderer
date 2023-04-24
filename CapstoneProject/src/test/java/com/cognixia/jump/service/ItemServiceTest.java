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

import org.assertj.core.util.Arrays;
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
import com.cognixia.jump.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
	@Mock
	private ItemRepository repo;
	
	@InjectMocks
	private ItemService service;
	
	@Test
	void testGetAllItems() throws Exception {
		List<Item> allItems = new ArrayList<Item>();
		allItems.add(new Item(1, null, "banana", "0.99", 4));
		allItems.add(new Item(1, null, "orange", "0.50", 2));
	
		when( repo.findAll() ).thenReturn(allItems);
		
		List<Item> result = service.getAllItems();
		
		for(int i = 0; i < allItems.size(); i++) {
			if( !allItems.get(i).equals( result.get(i) ) ) {
				fail();
			}
		}
	}
	
	@Test
	void testGetItemById() throws Exception {
		int id = 0;
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( repo.findById(id) ).thenReturn( Optional.of(item) );
		
		Item result = service.getItemById( id );
		
		assertEquals(item, result);
	}
	
	@Test
	void testGetItemByIdNotFound() throws Exception {
		int id = 1;
		
		when( repo.findById(id) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.getItemById(id);
		});
	}
	
	@Test
	void testAddItem() throws Exception {
		int id = 1;
		Item item = new Item(1, null, "banana", "0.99", 4);
		item.setUser( null );
	
		when( repo.save( Mockito.any( Item.class ) ) ).thenReturn( item );
		
		Item result = service.addItem( item );
		
		assertEquals(item, result);
	}
	
	@Test
	void testUpdateItem() throws Exception {
		int id = 1;
		Item item = new Item(1, null, "banana", "0.99", 4);
		item.setOrder( new Orders(1, Progress.COMPLETED, null) );
	
		when( repo.existsById( id ) ).thenReturn( true );
		when( repo.save( Mockito.any( Item.class ) ) ).thenReturn( item );
		
		Item result = service.updateItem( item );
		
		assertEquals(item, result);
	}
	
	@Test
	void testUpdateItemNotFound() throws Exception {
		int id = 1;
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( repo.existsById(id) ).thenReturn(false);
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.updateItem(item);
		});
	}
	
	@Test
	void testDeleteItem() throws Exception {
		int id = 1;
		Item item = new Item(1, null, "banana", "0.99", 4);
		
		when( repo.findById(id) ).thenReturn( Optional.of(item) );
		service.deleteItem( id );
		
		verify( repo, times(1) ).deleteById( id );
		verifyNoMoreInteractions( repo );
	}
	
	@Test
	void testDeleteItemNotFound() throws Exception {
		int id = 1;
		
		when( repo.findById( id ) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.deleteItem( id );
		});
	}
}
