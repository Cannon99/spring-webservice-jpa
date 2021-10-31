package com.thiagofurlan.springmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagofurlan.springmongodb.entities.Order;
import com.thiagofurlan.springmongodb.entities.User;
import com.thiagofurlan.springmongodb.repositories.OrderRepository;
import com.thiagofurlan.springmongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		User user1 = new User(null, "Rei", "rei@rei", "9999", "1234");
		User user2 = new User(null, "Cannón", "cannon@cannon", "9999", "1234");
	
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), user1);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), user2);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), user2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
	}
	
	
}
