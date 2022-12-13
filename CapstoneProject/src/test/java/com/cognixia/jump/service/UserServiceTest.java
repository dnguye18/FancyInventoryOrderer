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
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Orders.Progress;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository repo;
	
	@InjectMocks
	private UserService service;
	
	@Test
	void testGetAllUsers() throws Exception {
		List<User> allUsers = new ArrayList<User>();
		allUsers.add(new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true));
		allUsers.add(new User(2, "test2", "pw123", Role.ROLE_USER, "Sir", "McTester", "098-765-4321", true));
		
		when( repo.findAll() ).thenReturn(allUsers);
		
		List<User> result = service.getAllUsers();
		
		for(int i = 0; i < allUsers.size(); i++) {
			if( !allUsers.get(i).equals( result.get(i) ) ) {
				fail();
			}
		}
	}
	
	@Test
	void testGetUserById() throws Exception {
		int id = 1;
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( repo.findById(id) ).thenReturn( Optional.of(user) );
		
		User result = service.getUserById(id);
		
		assertEquals(user, result);
	}
	
	@Test
	void testGetUserByIdNotFound() throws Exception {
		int id = 1;
		
		when( repo.findById(id) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.getUserById(id);
		} );
	}
	
	@Test
	void testCreateUser() throws Exception {
		//TODO
		
		
		//TODO
	}
	
	@Test
	void testUpdateUser() throws Exception {
		int id = 1;
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(new Orders(1, Progress.COMPLETED, null));
		orders.add(new Orders(1, Progress.IN_PROGRESS, null));
		user.setOrders(orders);
		
		when( repo.existsById( id ) ).thenReturn(true);
		when( repo.save( user ) ).thenReturn( user );
		
		User result = service.updateUser( user );
		
		assertEquals(user, result);
	}
	
	@Test
	void testUpdateUserNotFound() throws Exception {
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( repo.existsById( user.getId() ) ).thenReturn( false );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.updateUser(user);
		} );
	}
	
	@Test
	void testDeleteUser() throws Exception {
		int id = 1;
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( repo.findById(id) ).thenReturn( Optional.of(user) );
		service.deleteUser(id);
		
		verify( repo, times(1) ).deleteById(id);
		verifyNoMoreInteractions( repo );
	}
	
	@Test
	void testDeleteUserNotFound() throws Exception {
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
	
		when( repo.findById( user.getId() ) ).thenReturn( Optional.empty() );
		
		assertThrows( ResourceNotFoundException.class, () -> {
			service.deleteUser( user.getId() );
		} );
	}
}
