package com.thiagofurlan.springmongodb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagofurlan.springmongodb.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
