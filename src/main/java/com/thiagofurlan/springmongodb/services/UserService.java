package com.thiagofurlan.springmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.entities.User;
import com.thiagofurlan.springmongodb.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.get();	
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User newUser) {
		User user = repository.getById(id);
		updateData(user, newUser);
		return repository.save(user);
	}
	
	private void updateData(User user, User newUser) {
		user.setEmail(newUser.getEmail());
		user.setName(newUser.getName());
		user.setPhone(newUser.getPhone());
	}
}
