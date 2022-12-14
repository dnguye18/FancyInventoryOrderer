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
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.User.Role;
import com.cognixia.jump.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
	
	private static final String STARTING_URI = "http://localhost:8080/api";
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	
	@Test
	void testGetAllUsers() throws Exception {
		String uri = STARTING_URI + "/user";
		
		List<User> allUsers = new ArrayList<User>();
		allUsers.add(new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true));
		allUsers.add(new User(2, "test2", "pw123", Role.ROLE_USER, "Sir", "McTester", "098-765-4321", true));
		
		when( service.getAllUsers() ).thenReturn(allUsers);
		
		mvc.perform( get(uri) )
		.andDo( print() )
		.andExpect( status().isOk() )
		.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
		.andExpect( jsonPath("$.length()").value( allUsers.size() ) )
		.andExpect( jsonPath("$[0].id").value( allUsers.get(0).getId() ) )
		.andExpect( jsonPath("$[0].username").value( allUsers.get(0).getUsername() ) )
		.andExpect( jsonPath("$[0].password").value( allUsers.get(0).getPassword() ) )
		.andExpect( jsonPath("$[0].Role").value( allUsers.get(0).getRole() ) )
		.andExpect( jsonPath("$[0].first_name").value( allUsers.get(0).getFirst_name() ) )
		.andExpect( jsonPath("$[0].last_name").value( allUsers.get(0).getLast_name() ) )
		.andExpect( jsonPath("$[0].phone").value( allUsers.get(0).getPhone() ) )
		.andExpect( jsonPath("$[0].enabled").value( allUsers.get(0).isEnabled() ) )
		.andExpect( jsonPath("$[1].id").value( allUsers.get(1).getId() ) )
		.andExpect( jsonPath("$[1].username").value( allUsers.get(1).getUsername() ) )
		.andExpect( jsonPath("$[1].password").value( allUsers.get(1).getPassword() ) )
		.andExpect( jsonPath("$[1].role").value( allUsers.get(1).getRole() ) )
		.andExpect( jsonPath("$[1].first_name").value( allUsers.get(1).getFirst_name() ) )
		.andExpect( jsonPath("$[1].last_name").value( allUsers.get(1).getLast_name() ) )
		.andExpect( jsonPath("$[1].phone").value( allUsers.get(1).getPhone() ) )
		.andExpect( jsonPath("$[1].enabled").value( allUsers.get(1).isEnabled() ) )
		;
		
		verify( service, times(1) ).getAllUsers();
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void getUser() throws Exception {
		String uri = STARTING_URI + "/user/{id}";
		int id = 1;
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( service.getUserById(id) ).thenReturn( user );
	
		mvc.perform( get(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
			.andExpect( jsonPath("$.id").value( user.getId() ) )
			.andExpect( jsonPath("$.username").value( user.getUsername() ) )
			.andExpect( jsonPath("$.password").value( user.getPassword() ) )
			.andExpect( jsonPath("$.role").value( user.getRole() ) )
			.andExpect( jsonPath("$.first_name").value( user.getFirst_name() ) )
			.andExpect( jsonPath("$.last_name").value( user.getLast_name() ) )
			.andExpect( jsonPath("$.phone").value( user.getPhone() ) )
			.andExpect( jsonPath("$.enabled").value( user.isEnabled() ) )
		;
		
		verify( service, times(1) ).getUserById(id);
		verifyNoMoreInteractions( service );
	}
	
	@Test
	void testUserNotFound() throws Exception {
		String uri = STARTING_URI + "/user/{id}";
		int id = 1;
		
		when( service.getUserById(id) )
			.thenThrow( new ResourceNotFoundException("User", id) )
		;
		
		mvc.perform( get(uri, id) )
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testCreateUser() throws Exception {
		String uri = STARTING_URI + "/user";
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( service.addUser( Mockito.any(User.class) ) )
			.thenReturn(user);
		;
		
		mvc.perform( post(uri) 
				.content( asJsonString(user) )
				.contentType( MediaType.APPLICATION_JSON_VALUE )
			)
			.andDo( print() )
			.andExpect( status().isCreated() )
		;
	}
	
	@Test
	void testUpdateUser() throws Exception {
		String uri = STARTING_URI + "/user";
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( service.updateUser( Mockito.any(User.class) ) )
			.thenReturn(user)
		;
		
		mvc.perform( put(uri)
					.content( asJsonString(user) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
			)
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testUpdateUserNotFound() throws Exception {
		String uri = STARTING_URI + "/user";
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( service.updateUser( Mockito.any(User.class) ) )
			.thenThrow( new ResourceNotFoundException("User", user.getId() ) )
		;
		
		mvc.perform( put(uri)
					.content( asJsonString(user) )
					.contentType( MediaType.APPLICATION_JSON_VALUE )
			)
			.andDo( print() )
			.andExpect( status().isNotFound() )
		;
	}
	
	@Test
	void testDeleteUser() throws Exception {
		String uri = STARTING_URI + "/user";
		int id = 1;
		User user = new User(1, "test1", "pw123", Role.ROLE_USER, "Testy", "McTester", "123-456-7890", true);
		
		when( service.deleteUser(id) ).thenReturn(user);
		
		mvc.perform( delete(uri, id) )
			.andDo( print() )
			.andExpect( status().isOk() )
		;
	}
	
	@Test
	void testDeleteUserNotFound() throws Exception {
		String uri = STARTING_URI + "/user";
		int id = 1;
		
		when( service.deleteUser(id) )
			.thenThrow( new ResourceNotFoundException("User", id) )
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
