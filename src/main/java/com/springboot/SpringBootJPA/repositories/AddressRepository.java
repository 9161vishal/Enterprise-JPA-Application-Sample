package com.springboot.SpringBootJPA.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.SpringBootJPA.Entities.Addresses;

public interface AddressRepository extends CrudRepository<Addresses,Long> {
	
}
