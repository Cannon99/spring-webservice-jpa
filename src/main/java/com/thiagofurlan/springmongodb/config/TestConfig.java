package com.thiagofurlan.springmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagofurlan.springmongodb.entities.User;
import com.thiagofurlan.springmongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User user1 = new User(null, "Rei", "rei@rei", "9999", "1234");
		User user2 = new User(null, "Cannón", "cannon@cannon", "9999", "1234");
	
		userRepository.saveAll(Arrays.asList(user1, user2));
	}
	
	
}
