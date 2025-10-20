package com.springboot.SpringBootJPA.Entities;



import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "categories")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Byte id;
	
	@Column(name = "name")
	private String name;
	
	@Builder.Default
	@ToString.Exclude
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
	Set<Product> products=new HashSet<>();
	
	public void addProduct(Product product) {
		System.out.println("Products"+product);
		products.add(product);
		product.setCategory(this);
	}

	public Category(Byte id) {
		super();
		this.id = id;
	}
	
	
}
