package com.thiagofurlan.springmongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagofurlan.springmongodb.entities.Category;
import com.thiagofurlan.springmongodb.entities.Order;
import com.thiagofurlan.springmongodb.entities.OrderItem;
import com.thiagofurlan.springmongodb.entities.Product;
import com.thiagofurlan.springmongodb.entities.User;
import com.thiagofurlan.springmongodb.entities.enums.OrderStatus;
import com.thiagofurlan.springmongodb.repositories.CategoryRepository;
import com.thiagofurlan.springmongodb.repositories.OrderItemRepository;
import com.thiagofurlan.springmongodb.repositories.OrderRepository;
import com.thiagofurlan.springmongodb.repositories.ProductRepository;
import com.thiagofurlan.springmongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		orderRepository.deleteAll();
		categoryRepository.deleteAll();
		productRepository.deleteAll();
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
	
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User user1 = new User(null, "Rei", "rei@rei", "9999", "1234");
		User user2 = new User(null, "Cannón", "cannon@cannon", "9999", "1234");
	
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.DELIVERED, user2);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
	
		OrderItem orderItem1 = new OrderItem(order1, p1, 2, p1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, p3, 1, p3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, p3, 2, p3.getPrice());
		OrderItem orderItem4 = new OrderItem(order3, p5, 2, p5.getPrice());
	
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));
	}
}
