package com.springboot.SpringBootJPA.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.SpringBootJPA.Entities.Category;

public interface CategoryRepositry extends CrudRepository<Category,Long> {

}
