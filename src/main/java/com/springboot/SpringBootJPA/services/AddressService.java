package com.springboot.SpringBootJPA.services;

import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Addresses;
import com.springboot.SpringBootJPA.Entities.User;
import com.springboot.SpringBootJPA.repositories.AddressRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AddressService {
	private final AddressRepository addressRepository;
	
	@Transactional
	public void fetchAddressRelatedIntities() {
		Addresses addresses= addressRepository.findById(2l).orElseThrow();
		System.out.println(addresses.getUser().getEmail());
	}
	@Transactional
	public void persistRelated() {
		Addresses addresses=Addresses.builder()
				.city("Gonda")
				.state("up")
				.build();
	User user=User.builder()
			.name("Prakher")
			.email("prakher@gmail.comm")
			.password("124444")
			.build();
	addresses.setUser(user);
	user.addAddress(addresses);
	addressRepository.save(addresses);
	}
}
