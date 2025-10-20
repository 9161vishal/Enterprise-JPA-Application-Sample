package com.springboot.SpringBootJPA.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Profile;
import com.springboot.SpringBootJPA.repositories.ProfileRepositry;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.experimental.var;

@AllArgsConstructor
@Service
public class ProfileService {
	private final ProfileRepositry profileRepositry;
	
	@Transactional
	public void showProfileRelatedIntities() {
		Profile profile=profileRepositry.findById(1l).orElseThrow();
		System.out.println(profile.getUser());
	}
	
	@Transactional
	public void findByLoyeltyPointsGreaterThan(int val) {
		var profile= profileRepositry.findByLoyeltyPointsGreaterThan(val);
		profile.forEach(f->{
			System.out.println(f.getUser().getEmail());
			System.out.println(f.getId());});
		
	}
	
	@Transactional
	public void fetchProfilesBasedOnLoyeltyPonts(int val) {
		var profiles=profileRepositry.fetchProfilesBasedOnLoyeltyPonts(val);
		profiles
		.stream()
		.sorted(Comparator.comparing(p->p.getUser().getEmail()))
		.collect(Collectors.toList())
		.forEach(f->{
			System.out.println(f.getUser().getEmail());
			System.out.println(f.getId());
		});
	}
	
	@Transactional
	public void fetchProfilesBasedOnLoyeltyPontsWithDto(int val) {
		var profile= profileRepositry.fetchProfilesBasedOnLoyeltyPontsWithDto(val);
		profile.forEach(f->{
			System.out.println(f.getEmail());
			System.out.println(f.getId());});
		
	}
	
}
