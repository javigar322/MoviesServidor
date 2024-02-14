package com.api.movies.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.movies.dto.UserResponse;
import com.api.movies.entities.User;
import com.api.movies.repositories.UserRepository;
import com.api.movies.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserResponse> getAllUsers() {
		List<UserResponse> allUsers =  userRepository.findAll().stream()
			    .map(user -> new UserResponse(user.getFirstName(), user.getEmail() ))
			    .collect(Collectors.toList());
		 return allUsers;
	}
	
	@Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}
	@Override
	public UserResponse findUserById(Long id) {
		User userFound = userRepository.findById(id).orElseThrow();
		UserResponse user =  new UserResponse(userFound.getFirstName(), userFound.getEmail());
		 return user;
	}
	
}
