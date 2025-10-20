package com.springboot.SpringBootJPA.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Category;
import com.springboot.SpringBootJPA.Entities.Product;
import com.springboot.SpringBootJPA.repositories.CategoryRepositry;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
	private final CategoryRepositry categoryRepositry;
	
	public void setProductInExistingCategory() {
		Category category=categoryRepositry.findById(1l).get();
	    Product product=Product.builder()
	    		.name("Trimmer")
	    		.price(new BigDecimal(800.50))
	    		.build();
	    category.addProduct(product);
		System.out.println("Catogery"+category);
		System.out.println("Product.."+product);
		
		categoryRepositry.save(category);
	}
}
