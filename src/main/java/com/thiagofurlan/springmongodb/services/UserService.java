package com.thiagofurlan.springmongodb.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thiagofurlan.springmongodb.entities.User;
import com.thiagofurlan.springmongodb.exceptions.DatabaseException;
import com.thiagofurlan.springmongodb.exceptions.ResourceNotFoundException;
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
		return user.orElseThrow(() -> {
			throw new ResourceNotFoundException(id);
		});	
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}	
	}
	
	public User update(Long id, User newUser) {
		try {
			User user = repository.getById(id);
			updateData(user, newUser);
			return repository.save(user);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}
	
	private void updateData(User user, User newUser) {
		user.setEmail(newUser.getEmail());
		user.setName(newUser.getName());
		user.setPhone(newUser.getPhone());
	}
}
