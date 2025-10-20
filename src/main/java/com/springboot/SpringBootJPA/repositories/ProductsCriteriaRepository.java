package com.springboot.SpringBootJPA.repositories;

import java.math.BigDecimal;
import java.util.List;

import com.springboot.SpringBootJPA.Entities.Product;

public interface ProductsCriteriaRepository {
	List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
