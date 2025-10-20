package com.springboot.SpringBootJPA.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.SpringBootJPA.Entities.User;
import com.springboot.SpringBootJPA.dtos.UserSummary;

public interface UserRepository extends CrudRepository<User,Long>{
	@EntityGraph(attributePaths = {"tags","addresses"})
	Optional<User> findByEmail(String email);
	
	@EntityGraph(attributePaths = "addresses")
	List<User> findAll();
	
	List<UserSummary> findByProfileLoyeltyPointsGreaterThan(int loyeltyPoints);
	
	
	@Query(value = "select u.id as id,u.email as email from User u where u.profile.loyeltyPoints>:loyeltyPoints")
	List<UserSummary> trustedUsers(@Param("loyeltyPoints")int lyeltyPoints);
}
