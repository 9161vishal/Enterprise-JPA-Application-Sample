package com.springboot.SpringBootJPA.Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@ToString.Exclude
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private List<Addresses> addresses=new ArrayList<>();
	
	public void addAddress(Addresses address) {
		addresses.add(address);
		address.setUser(this);
	}
	
	public void removeAddress(Addresses address) {
		addresses.remove(address);
		address.setUser(null);
	}
	
	@ManyToMany
	@ToString.Exclude
	@JoinTable(
			name = "user_tag",
			joinColumns =@JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="tag_id")
			)
	
	
	@Builder.Default
	Set<Tag> tags=new HashSet<>();
	
	public void addTags(String tagsName) {
		Tag tag=new Tag(tagsName);
		tags.add(tag);
		tag.getUsers().add(this);
	}
	
	public void removeTags(Tag tag) {
		tags.remove(tag);
		tag.getUsers().remove(this);
	}
	
	@ToString.Exclude
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL) //--->Here we not declear fetching stretegy likr LAZY and EGER we only declear on owning side
	private Profile profile;
	
	
	
	@ToString.Exclude
	@ManyToMany
	@JoinTable(
			name="wishlist",
			joinColumns=@JoinColumn(name="user_id" ),
			inverseJoinColumns = @JoinColumn(name="product_id"))
	@Builder.Default
	private Set<Product> favProducts=new HashSet<>();
	
	public void addFavoraitProduct(Product p) {
		favProducts.add(p);
	}
	
	
}
