package com.springboot.SpringBootJPA.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.SpringBootJPA.Entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

}
