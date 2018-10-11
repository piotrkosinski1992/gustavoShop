package com.ecommerce2.gustavoShop.service;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecommerce2.gustavoShop.model.User;
import com.ecommerce2.gustavoShop.repository.RoleRepository;
import com.ecommerce2.gustavoShop.repository.UserRepository;

public class UserServiceTest {

	@Mock
	UserRepository userRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@InjectMocks
	UserService userService;
	
	User tempUser;
		
	//SetUp
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		tempUser = User.builder().name("piotr").email("test@test.com").id(1).build();

		Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(tempUser);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(tempUser);
	}
	
	
	//testFindUserByEmail
	@Test
	public void findUserByEmailTest() {
		
		assertTrue(userRepository.findByEmail("test@test.com").getEmail() == tempUser.getEmail());
	}
	
	//TestAddUser
	@Test
	public void saveUserTest() {
		
		User result = userService.saveUser(tempUser);
		
		assertTrue("test@test.com" == result.getEmail());
	}
}
