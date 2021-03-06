package com.ecommerce2.gustavoShop.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce2.gustavoShop.model.Role;
import com.ecommerce2.gustavoShop.model.User;
import com.ecommerce2.gustavoShop.repository.RoleRepository;
import com.ecommerce2.gustavoShop.repository.UserRepository;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
			this.userRepository = userRepository;
			this.roleRepository = roleRepository;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
		return user;
	}
}
