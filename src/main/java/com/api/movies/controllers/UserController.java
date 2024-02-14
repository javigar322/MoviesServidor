package com.api.movies.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.dto.UserResponse;
import com.api.movies.entities.User;
import com.api.movies.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserResponse> getAllUser() {
		logger.info("ha entrado");
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserResponse getUser(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
}
