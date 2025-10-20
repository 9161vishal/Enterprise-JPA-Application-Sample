package com.springboot.SpringBootJPA.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.SpringBootJPA.Entities.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
class ProductsCriteriaRepositoryImpl implements ProductsCriteriaRepository {
	@PersistenceContext
	private final EntityManager entityManager;
	
	@Override
	public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq=cb.createQuery(Product.class);
		
		// It Just like from this table
		Root<Product> root=cq.from(Product.class);
		
		List<Predicate> predicates=new ArrayList<>();
		if(name!=null) {
			// like name %name% (user input name)
			predicates.add(cb.like(root.get("name"),"%"+name+"%"));
		}
		if(minPrice!=null) {
			// price>= name %minPrice% (user input price)
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"),minPrice));
		}
		if(maxPrice!=null) {
			// price>= name %maxPrice% (user input price)
			predicates.add(cb.lessThanOrEqualTo(root.get("price"),maxPrice));
		}
		
		//select query where listed predicate (converting list of predicate to Array of predicate
		cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		
		return entityManager.createQuery(cq).getResultList();
	}
	
	
	
}
