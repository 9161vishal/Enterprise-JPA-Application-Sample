package com.springboot.SpringBootJPA;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.SpringBootJPA.Entities.Addresses;
import com.springboot.SpringBootJPA.Entities.Profile;
import com.springboot.SpringBootJPA.Entities.Tag;
import com.springboot.SpringBootJPA.Entities.User;
import com.springboot.SpringBootJPA.repositories.ProductRepositry;
import com.springboot.SpringBootJPA.repositories.UserRepository;
import com.springboot.SpringBootJPA.services.AddressService;
import com.springboot.SpringBootJPA.services.CategoryService;
import com.springboot.SpringBootJPA.services.ProductService;
import com.springboot.SpringBootJPA.services.ProfileService;
import com.springboot.SpringBootJPA.services.TagService;
import com.springboot.SpringBootJPA.services.UserService;





@SpringBootApplication
public class SpringBootJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(SpringBootJpaApplication.class, args);
//		UserService userService= context.getBean(UserService.class);
		ProductService productService=context.getBean(ProductService.class);
//		CategoryService categoryService=context.getBean(CategoryService.class);
//		TagService tagService=context.getBean(TagService.class);
//		ProfileService profileService=context.getBean(ProfileService.class);

		
//		userService.showRelatedIntities();
		
//		ProfileService profileService=context.getBean(ProfileService.class);
//		profileService.showProfileRelatedIntities();
		
//		AddressService addressService=context.getBean(AddressService.class);
//		addressService.fetchAddressRelatedIntities();
		
//		userService.persistRelated();
		
//		userService.deleteRelated();
//		addressService.persistRelated();
		
//		userService.removeAddresByUser();
		
//		productService.insertProduct();
		
//		categoryService.setProductInExistingCategory();
		//userService.addProductInWishlist();
//		userService.dletProductsFromWishList();/
//		productService.fetchByDTOClassUsingQuery();
		
//		tagService.setUser();
//		userService.findByEmail();
		
		
//		Profile profile=Profile.builder()
//				.bio("Civil Servent")
//				.date_of_birthDate(LocalDate.of(2000, 4, 11))
//				.loyeltyPoints(20)
//				.phoneNumber(6611660011l)
//				.build();
//		userService.addProfile(11l,profile);
//		
		
//		profileService.fetchProfilesBasedOnLoyeltyPontsWithDto(4);
		
//		userService.trustedUsers(4);
		
		productService.fetchSortedPaginatedProduct(0,5);
		
		
		
/*	repository.save(user1);
		

	User user= User.builder()
			.name("Vishal Chaudhary")
			.email("9161vishal@gmail.com")
			.password("vishal123")
			.build();
		
		Addresses addresses= Addresses.builder()
				 .street("2nd Cross")
				 .street("Banglore")
				 .zip("560076")
				 .state("Karnataka")
				 .build();
		
		user.addAddress(addresses);
		System.out.println(user);
		
	1.	user.getAddresses().add(addresses);
	 *           ^
	 *           |
	 *     This return the list of addresses(which is a list variable addresses) 
	 *     and in this list add one more address (just like l1.add())
	 *     If we try to set addresses with the help of setter method then it asking for list type input 
	 *     and here we have address object only not a list of addresses thats why we first get that list
	 *     then we further added addresses
		2.addresses.setUser(user);
		Before: before performing these two lines we have
			┌───────────┐          ┌────────────┐
			│  Vishal   │          │  Address   │
			│addresses: │   ❌ No link yet ❌   │
			│   []      │          │ user: null │
			└───────────┘          └────────────┘

			After: Performing these two lines
			┌───────────┐                   ┌─────────────┐
			│  Vishal   |◄────────▶ |  Address    |
			│addresses: │                   │user: Vishal │
			│ [Address] │                   │             │
			└───────────┘                   └─────────────┘

		
		
//		Tag tags=new Tag("Tag1");
//		user.getTags().add(tags);  //-----> Insted of using these three lines we create a method in user class
//		tags.getUsers().add(user);/ to encapsulate the addTag in user class
		
//		user.addTags("Tag1");
//		System.out.println(user);
		
//		
//		Profile profile=Profile.builder()
//					.bio("Vishal profiles..")
//					.build();
//		
//		user.setProfile(profile);
//		profile.setUser(user);
//		
//		System.out.println(profile);
//		System.out.println(user);
		*/
	}

}
