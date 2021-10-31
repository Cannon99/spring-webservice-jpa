package com.thiagofurlan.springmongodb.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagofurlan.springmongodb.entities.User;

@RestController
@RequestMapping(path = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> getUser() {
		User user = new User(1L, "Rei", "rei@rei", "999", "1234");
		return ResponseEntity.ok(user);
	}

}
