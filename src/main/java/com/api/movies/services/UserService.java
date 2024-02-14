package com.api.movies.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.api.movies.dto.UserResponse;
import com.api.movies.entities.User;

public interface UserService {

	User createUser(User user);
	UserDetailsService userDetailsService();
    List<UserResponse> getAllUsers();
	UserResponse findUserById(Long id);

}
