package com.springboot.SpringBootJPA.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.springboot.SpringBootJPA.Entities.Product;


public class ProductSpecificaton {
	public static Specification<Product> hasName(String name){
		return (root,cq,cb)->cb.like(root.get("name"), "%"+name+"%");
		
		//For case sensitive
//		return (root,cq,cb)->cb.or(cb.like(cb.upper(root.get("name")), "%"+name.toUpperCase()+"%"),cb.like(cb.lower(root.get("name")), "%"+name.toLowerCase()+"%"));
	}
	
	public static Specification<Product> lessThanOrEqualTo(BigDecimal maxPrice){
		return (root,cq,cb)->cb.lessThanOrEqualTo(root.get("price"), maxPrice);
	}
	
	public static Specification<Product> greaterThanOrEqualTo(BigDecimal minPrice){
		return (root,cq,cb)->cb.greaterThanOrEqualTo(root.get("price"), minPrice);
	}
}
