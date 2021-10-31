package com.thiagofurlan.springmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagofurlan.springmongodb.entities.Order;
import com.thiagofurlan.springmongodb.services.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderResource {
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orders = service.findAll();
		return ResponseEntity.ok(orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Order order = service.findById(id);
		return ResponseEntity.ok(order);
	}

}
