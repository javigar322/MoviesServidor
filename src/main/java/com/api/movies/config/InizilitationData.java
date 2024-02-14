package com.api.movies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.movies.entities.Role;
import com.api.movies.entities.User;
import com.api.movies.repositories.UserRepository;

@Component
public class InizilitationData implements CommandLineRunner {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	
	@Override
	public void run(String... args) throws Exception {
		
		try {
			// user 1
			User user1 = new User();
			user1.setFirstName("javier");
			user1.setLastName("gar");
			user1.setEmail("javigar@gmail.com");
			user1.setPassword(passwordEncoder.encode("password"));
			user1.getRoles().add(Role.ROLE_USER);
			userRepository.save(user1);
			
			// user 2
			User user2 = new User();
			user2.setFirstName("pana");
			user2.setLastName("miguel");
			user2.setEmail("miguel@gmail.com");
			user2.setPassword(passwordEncoder.encode("password"));
			user2.getRoles().add(Role.ROLE_ADMIN);
			userRepository.save(user2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
