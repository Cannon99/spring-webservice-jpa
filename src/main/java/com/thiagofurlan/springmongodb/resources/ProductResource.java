package com.thiagofurlan.springmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagofurlan.springmongodb.entities.Product;
import com.thiagofurlan.springmongodb.services.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductResource {
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> getCategories() {
		List<Product> products = service.findAll();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok(product);
	}

}
