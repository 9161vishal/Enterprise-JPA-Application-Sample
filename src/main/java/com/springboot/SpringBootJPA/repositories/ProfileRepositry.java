package com.springboot.SpringBootJPA.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.SpringBootJPA.Entities.Profile;
import com.springboot.SpringBootJPA.dtos.UserSummary;

public interface ProfileRepositry extends CrudRepository<Profile,Long> {
	
	@EntityGraph(attributePaths = "user")
	List<Profile> findByLoyeltyPointsGreaterThan( int loyeltyPoints);
	
	
	@EntityGraph(attributePaths = "user")
	@Query(value="select p from Profile p where p.loyeltyPoints>:loyeltyPonits")
	List<Profile> fetchProfilesBasedOnLoyeltyPonts(@Param("loyeltyPonits") int loyeltyPoints);
	
	@EntityGraph(attributePaths = "user")
	@Query(value="select p.id as id,p.user.email as email from Profile p where p.loyeltyPoints>:loyeltyPonits")
	List<UserSummary> fetchProfilesBasedOnLoyeltyPontsWithDto(@Param("loyeltyPonits") int loyeltyPoints);
}
