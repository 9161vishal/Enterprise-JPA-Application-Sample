package com.springboot.SpringBootJPA.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springboot.SpringBootJPA.Entities.Category;
import com.springboot.SpringBootJPA.Entities.Product;
import com.springboot.SpringBootJPA.repositories.ProductRepositry;
import com.springboot.SpringBootJPA.specifications.ProductSpecificaton;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.experimental.var;

@Service
@AllArgsConstructor
public class ProductService {
	private final ProductRepositry productRepositry;
	
	@Transactional
	public void insertProduct() {
		
		Product product=Product.builder()
				.name("Fan")
				.price(new BigDecimal(1200.50))
				.build();
		Category category=Category.builder()
				.name("Electronic")
				.build();
		product.setCategory(category);
		productRepositry.save(product);
	}
//=======================================================================================================================
	//    FETCHING OPERATION BASED ON STRINGS
//=======================================================================================================================	
	
	public List<Product> findByName(String name){
		return productRepositry.findByName(name);
	}
	
	public List<Product> findByNameLike(String name){
		return productRepositry.findByNameLike(name);
	}
	
	public List<Product> findByNameLikeIgnoreCase(String name){
		return productRepositry.findByNameLikeIgnoreCase(name);
	}
	
	public List<Product> findByNameNotLike(String name){
		return productRepositry.findByNameNotLike(name);
	}
	
	
	public List<Product> findByNameContains(String name){
		return productRepositry.findByNameContains(name);
	}
	
	public List<Product> findByNameStartsWith(String name){
		return productRepositry.findByNameStartsWith(name);
	}
	
	public List<Product> findByNameStartsWithIgnoreCase(String name){
		return productRepositry.findByNameStartsWithIgnoreCase(name);
	}
	
	public List<Product> findByNameEndsWith(String name){
		return productRepositry.findByNameEndsWith(name);
	}
	
//=======================================================================================================================
	//            FETCHING OPERATIONS BASED ON NUMBER	
//=======================================================================================================================
	public List<Product> findByPrice(BigDecimal price){
		return productRepositry.findByPrice(price);
	}
	
	public List<Product> findByPriceGreaterThan(BigDecimal price){
		return productRepositry.findByPriceGreaterThan(price);
	}
	
	public List<Product> findByPriceGreaterThanEqual(BigDecimal price){
		return productRepositry.findByPriceGreaterThanEqual(price);
	}
	
	public List<Product> findByPriceLessThan(BigDecimal price){
		return productRepositry.findByPriceLessThan(price);
	}
	
	public List<Product> findByPriceLessThanEqual(BigDecimal price){
		return productRepositry.findByPriceLessThanEqual(price);
	}
	
	public List<Product> findByPriceBetween(BigDecimal min,BigDecimal max){
		return productRepositry.findByPriceBetween(min,max);
	}
	
//========================================================================================================================
	//       FETCHING OPERATION BASED ON NULL  VALUE 
//=======================================================================================================================
	
	public List<Product> findByPriceNull(){
		return productRepositry.findByPriceNull();
	}
	
	public List<Product> findByPriceNotNull(){
		return productRepositry.findByPriceNotNull();
	}
	
//=======================================================================================================================
	//       SOME LOGICAL OPERATER ARE ALSO USED NULL VALUES
//=======================================================================================================================
	
	public List<Product> findByPriceNullAndNameNull(){
		return productRepositry.findByPriceNullAndNameNull();
	}
	
//=======================================================================================================================
	//              ORDER BY USING TO SHORT
//=======================================================================================================================
	
	public List<Product> findByNameOrderByPrice(String name){
		return productRepositry.findByNameOrderByPrice(name);
	}
	
	
	public List<Product> findByNameIsNotNullOrderByNameAsc(){
		return productRepositry.findByNameIsNotNullOrderByNameAsc();
	}
	
	public List<Product> findByNameContainsOrderByPriceDesc(String subString){
		return productRepositry.findByNameContainsOrderByPriceDesc(subString);
	}
	
//=======================================================================================================================
	//          LIMITS (Top/First,Last)
//=======================================================================================================================
	
	public List<Product> findFirst5ByNameOrderByPriceAsc(String name) {
		return productRepositry.findFirst5ByNameOrderByPriceAsc(name);
	}
	
	public List<Product> findTop5ByNameLikeOrderByPriceDesc(String name){
		return productRepositry.findTop5ByNameLikeOrderByPriceDesc(name);
	}
	
//=======================================================================================================================
	//           USING @QUERY 
//=======================================================================================================================
	
	public List<Product> findProducts(BigDecimal min,BigDecimal max){
		return productRepositry.findProducts(min, max);
	}
	
//=======================================================================================================================
	//       USING JPQL QUERY WITH AGRIGATE FUNCTION LIKE COUNT
//=======================================================================================================================
	
	public long countProducts(BigDecimal min,BigDecimal max) {
		return productRepositry.countProducts(min, max);
	}
	
//=======================================================================================================================
	//         UPDATE OPERATION BY JPQL QUERY
//=======================================================================================================================
	@Transactional//--------------> must apply in update query methods
	public void updatePriceByCategory(BigDecimal newPrice,Byte categoryId) {
		productRepositry.updatePriceByCategory(newPrice, categoryId);
		System.out.println("PRICE IS UPDATED ...");
	}
//=======================================================================================================================
	//         FIND PRODUCT BY CATEGORY
//=======================================================================================================================
//	public void findProductByCategory() {
//		var products=productRepositry.findByCategory(new Category((byte)3));
//		products.forEach(System.out::println);
//	}
	
//=======================================================================================================================
	//           NOW FIND PRODUCT ID AND NAME ONLY BY CATOGRY(USING DTOS INTERFACE)
//=======================================================================================================================
//	public void findProductByCategory() {
//		var products=productRepositry.findByCategory(new Category((byte)3));
//		products.forEach(p->{
//			System.out.println("ID--> "+p.getId()+" NAME---> "+p.getName());
//		});
//	}
	
//=======================================================================================================================
		//           NOW FIND PRODUCT ID AND NAME ONLY BY CATOGRY(USING DTOS CLASS)
//=======================================================================================================================
	public void findProductByCategory() {
		var products=productRepositry.findByCategory(new Category((byte)3));
		products.forEach(p->{
			System.out.println("ID--> "+p.id+" NAME---> "+p.name);
		});
	}
		
//=======================================================================================================================
		//         DTOS USING QUERY(WITH INTERFACE)
//=======================================================================================================================
		public void fetchProductByCategory() {
			var products=productRepositry.fetchByCategory(new Category((byte)3));
			products.forEach(p->{
				System.out.println("ID--> "+p.getId()+" NAME---> "+p.getName());
			});
		}
		
//=======================================================================================================================
//           DTOS USING QUERY(WITH CLASS)
//=======================================================================================================================
		public void findByProductSummaryDTOs() {
			var products=productRepositry.findByProductSummaryDTOs(new Category((byte)3));
			products.forEach(p->{
				System.out.println("ID--> "+p.getId()+" NAME---> "+p.getName());
			});
		}
		
//=======================================================================================================================
//      DTOS USING QUERY FOR SELECTING ONLY TWO FIELDS(WITH CLASS)
//=======================================================================================================================
		public void fetchByDTOClassUsingQuery() {
			var products=productRepositry.fetchByDTOClassUsingQuery(new Category((byte)3));
			products.forEach(p->{
				System.out.println("ID--> "+p.getId()+" NAME---> "+p.getName());
			}); 
		}
		
//=======================================================================================================================
//      DYNAMIC QUERIES   USING EXAMPLE ENTITY
//=======================================================================================================================
		public void findProductsByUsingExampleEntity(String productName) {
			Product probe=new Product();
			probe.setName(productName);//this is find exact matching string in the name of product and in query where clause is generated
			Example<Product> example=Example.of(probe);
			List<Product> products= productRepositry.findAll(example);
			products.forEach(p->System.out.println(p));
		}
		
		public void findProductsByUsingExampleEntityMatcher(String productName) {
			Product probe=new Product();
			probe.setName(productName);
			ExampleMatcher matcher=ExampleMatcher
					.matching()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//this find any name contains given string matching with name or name contain matching string
			Example<Product> example=Example.of(probe,matcher);
			List<Product> products= productRepositry.findAll(example);
			products.forEach(p->System.out.println(p));
		}
		
		public void findProductsByUsingExampleMatcherNullValues(String productName) {
			Product probe=new Product();
			probe.setName(productName);
			probe.setPrice(new BigDecimal(1200.0d));
			ExampleMatcher matcher=ExampleMatcher
					.matching()
					.withIgnoreCase(true)
					.withIncludeNullValues()
					.withIgnorePaths("id","category","name")
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//this find any name contains given string matching with name or name contain matching string
			Example<Product> example=Example.of(probe,matcher);
			List<Product> products= productRepositry.findAll(example);
			products.forEach(p->System.out.println(p));
		}
		
//=======================================================================================================================
//      DYNAMIC QUERIES   USING CRETERIA API
//=======================================================================================================================
		public void fetchProductsByCreteria(String name, BigDecimal min, BigDecimal max) {
			List<Product> products=productRepositry.findProductsByCriteria(name, min, max);
			products.forEach(System.out::println);
		}

//=======================================================================================================================
//      DYNAMIC QUERIES   USING SPECIFICATON API
//=======================================================================================================================
		public void findProductsBySpecification(String name,BigDecimal min, BigDecimal max){
			Specification<Product> specification=Specification.where(null);
			if(name!=null) {
				specification= specification.and(ProductSpecificaton.hasName(name));
			}
			
			if(max!=null) {
				specification= specification.and(ProductSpecificaton.lessThanOrEqualTo(max));
			}
			
			if(min!=null) {
				specification= specification.and(ProductSpecificaton.greaterThanOrEqualTo(min));
			}
			
			productRepositry.findAll(specification).forEach(System.out::println);
		}
		
//=======================================================================================================================
//      USING OF SORT (IN PagingAndSortingRepository)
//=======================================================================================================================		
		public void sortedProductBySort() {
			Sort sort=Sort.by("name").ascending().and(Sort.by("price").descending());
			productRepositry.findAll(sort).forEach(System.out::println);
		}
		
		
		public void fetchPaginatedProduct(int pageNo,int size) {
			PageRequest pageRequest=PageRequest.of(pageNo, size);
			Page<Product> page=productRepositry.findAll(pageRequest);
			page.getContent().forEach(System.out::println);
			int totalPages= page.getTotalPages();
			int totalElements=page.getNumberOfElements();
			System.out.println("Total Pages "+totalPages);
			System.out.println("Total Elements "+totalElements);
			
		}
		
		public void fetchSortedPaginatedProduct(int pageNo,int size) {
			Sort sort=Sort.by("name").ascending().and(Sort.by("price").descending());
			PageRequest pageRequest=PageRequest.of(pageNo, size,sort);
			Page<Product> page=productRepositry.findAll(pageRequest);
			page.getContent().forEach(System.out::println);
			int totalPages= page.getTotalPages();
			int totalElements=page.getNumberOfElements();
			System.out.println("Total Pages "+totalPages);
			System.out.println("Total Elements "+totalElements);
			
		}
}
