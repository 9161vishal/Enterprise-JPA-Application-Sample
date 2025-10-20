package com.springboot.SpringBootJPA.services;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Addresses;
import com.springboot.SpringBootJPA.Entities.Profile;
import com.springboot.SpringBootJPA.Entities.User;
import com.springboot.SpringBootJPA.repositories.ProductRepositry;
import com.springboot.SpringBootJPA.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.experimental.var;

@AllArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository ;
	private final EntityManager entityManager;
	private final ProductRepositry productRepositry;
	
	@Transactional
	public void showUserIntityStates() {
		User user=User.builder()
				.name("Vishal")
				.email("9161vishal@gmail.com")
				.password("123456")
				.build();
		
		if(entityManager.contains(user)) System.out.println("Persistant");
		else {
			System.out.println("Transient/Detached");
		}
		
		userRepository.save(user);
		
		if(entityManager.contains(user)) System.out.println("Persistant");
		else {
			System.out.println("Transient/Detached");
		}
	}
	
	public void showRelatedIntities() {
		User user=userRepository.findById(2l).orElseThrow();
		System.out.println(user.getEmail());
	}
	
	public void persistRelated() {
		User user=User.builder()
				.name("Rajendra")
				.email("Rajendra123@gmail.com")
				.password("442233")
				.build();
		Addresses addresse=Addresses.builder()
				.city("Shivpur")
				.state("Balrampur")
				.build();
		user.addAddress(addresse);
		userRepository.save(user);
	}
	
	public void deleteRelated() {
		userRepository.deleteById(1l);
	}
	
	@Transactional
	public void removeAddresByUser() {
		User user= userRepository.findById(8l).orElseThrow();
		Addresses addresses=user.getAddresses().get(0);
		user.removeAddress(addresses);
		userRepository.save(user);
		
	}
	
	@Transactional
	public void addProductInWishlist() {
		User user=userRepository.findById(12l).orElseThrow();
		var products=productRepositry.findAll();
		products.forEach(product->{user.addFavoraitProduct(product);});
		userRepository.save(user);		
	}
	
	@Transactional
	public void dletProductsFromWishList() {
		productRepositry.deleteById(24l);
	}
	
	@Transactional
	public void findByEmail() {
		var user=userRepository.findByEmail("nagendra234@gmail.com").orElseThrow();
		System.out.println(user);
	}
	
	@EntityGraph(attributePaths = "addresses")
	@Transactional
	public void findAllUsers() {
		var user=userRepository.findAll();
		user.forEach(t ->{
			System.out.println(t);
			t.getAddresses().forEach(System.out::println);
		});
	}
	
	@Transactional
	public void addProfile(Long id, Profile profile) {
		var userOpt=userRepository.findById(id);
		User user=userOpt.get();
		if(user!=null&&profile!=null) {
			profile.setUser(user);
			user.setProfile(profile);
			userRepository.save(user);
			
		}
	}
	
	public void findByLoyeltyPointsGreaterThan(int loyeltyPoints) {
		var users=userRepository.findByProfileLoyeltyPointsGreaterThan(loyeltyPoints);
		users.forEach(u->{
			System.out.println(u.getEmail());
			System.out.println(u.getId());
		});
	}
	
	public void trustedUsers(int loyeltyPoints) {
		var users =userRepository.trustedUsers(loyeltyPoints);
		users.forEach(u->{
			System.out.println(u.getEmail());
			System.out.println(u.getId());
		});
	}
	 
}
