package com.springboot.SpringBootJPA.services;

import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Tag;
import com.springboot.SpringBootJPA.Entities.User;
import com.springboot.SpringBootJPA.repositories.TagRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TagService {
	private final String cat1="Normal User";
	private final String cat2="Platinum User";
	private final String cat3="Dimond";
	private final TagRepository tagRepository;
	
	public Tag genrateTagForNormalUser() {
		return new Tag(cat1);
	}
	
	public Tag genrateTagForPlatinumUser() {
		return new Tag(cat2);
	}
	
	public Tag genrateTagForDimondumUser() {
		return new Tag(cat3);
	}
	
	public void setUser() {
		User user=User.builder()
				.name("Nagendra")
				.email("nagendra234@gmail.com")
				.password("12345678")
				.build();
		Tag tag=genrateTagForNormalUser();
		tag.addUser(user);
		tagRepository.save(tag);
	}
	
}
