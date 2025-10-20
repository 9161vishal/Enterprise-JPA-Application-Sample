package com.springboot.SpringBootJPA.dtos;

public interface ProductSummary {
	// A confusion is arise how these methods initialize and who provide implementing object for them 
	// then answer is internally Spring Boot Data JPA , after fetching data from data base it create object for that
	// and matches fields. If fields are matching then create object and return;
	Long getId();
	String getName();
}
