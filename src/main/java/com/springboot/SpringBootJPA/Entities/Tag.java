package com.springboot.SpringBootJPA.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;

	public Tag(String name) {
		this.name = name;
	}

	@ToString.Exclude
	@ManyToMany(mappedBy = "tags",cascade = CascadeType.PERSIST)
	Set<User> users=new HashSet<>();
	
	public void addUser(User user) {
		users.add(user);
		user.tags.add(this);
	}
}
