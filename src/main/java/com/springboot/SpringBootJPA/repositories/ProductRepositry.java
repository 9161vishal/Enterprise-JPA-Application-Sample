package com.springboot.SpringBootJPA.repositories;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.SpringBootJPA.Entities.Category;
import com.springboot.SpringBootJPA.Entities.Product;
import com.springboot.SpringBootJPA.dtos.ProductSummary;
import com.springboot.SpringBootJPA.dtos.ProductSummaryDTO;

public interface ProductRepositry extends JpaRepository<Product,Long>, ProductsCriteriaRepository,JpaSpecificationExecutor<Product>{
/*-----------------------------------------------------------------------------------------------------------------------
 *                          FOR STRING OR SUBSTRING
 * ---------------------------------------------------------------------------------------------------------------------*/
	List<Product> findByName(String name);
	List<Product> findByNameLike(String name);//In this only "=" ----> Like operator nothing will happend it is same as =
	List<Product> findByNameLikeIgnoreCase(String name);
	List<Product> findByNameNotLike(String name);
	List<Product> findByNameContains(String name);
	List<Product> findByNameStartsWith(String name);
	List<Product> findByNameEndsWith(String name);
	List<Product> findByNameStartsWithIgnoreCase(String name);
	
/*-----------------------------------------------------------------------------------------------------------------------
	 *                FOR NUMBERS
 ---------------------------------------------------------------------------------------------------------------------*/
	List<Product> findByPrice(BigDecimal price);
	List<Product> findByPriceGreaterThan(BigDecimal price);
	List<Product> findByPriceGreaterThanEqual(BigDecimal price);
	List<Product> findByPriceLessThan(BigDecimal price);
	List<Product> findByPriceLessThanEqual(BigDecimal price);
	List<Product> findByPriceBetween(BigDecimal min,BigDecimal max);
	
/*-----------------------------------------------------------------------------------------------------------------------
	 *               FOR NULL OPERATIONS
 ---------------------------------------------------------------------------------------------------------------------*/	
	List<Product> findByPriceNull();
	List<Product> findByPriceNotNull();

/*-----------------------------------------------------------------------------------------------------------------------
	 *               MULTIPLE CONDITIONS
 ---------------------------------------------------------------------------------------------------------------------*/	
	List<Product> findByPriceNullAndNameNull();

/*-----------------------------------------------------------------------------------------------------------------------
	 *               SORTING BASED FETCHING(ORDERBY)
 ---------------------------------------------------------------------------------------------------------------------*/
	List<Product> findByNameOrderByPrice(String name);
	List<Product> findByNameIsNotNullOrderByNameAsc();
	List<Product> findByNameContainsOrderByPriceDesc(String subString);
	
/*-----------------------------------------------------------------------------------------------------------------------
	 *               LIMITS (Top/First)
 ---------------------------------------------------------------------------------------------------------------------*/	
	List<Product> findFirst5ByNameOrderByPriceAsc(String name);
	List<Product> findTop5ByNameLikeOrderByPriceDesc(String name);

/*-----------------------------------------------------------------------------------------------------------------------
	 *               BY USING @QUERY
 ---------------------------------------------------------------------------------------------------------------------*/	
	//List<Product> findByPriceBetweenOrderByName(BigDecimal min,BigDecimal max);
	//                                   ^
	//                                   |
	//                          Insted of this we use @Query to make method name simple
	
	//   USING ----> SQL QUERY
//	@Query(value = "select * from products p where p.price between :min and :max order by p.name", nativeQuery = true)
//	List<Product> findProducts(@Param("min") BigDecimal min,@Param("max")BigDecimal max);
	
	// USING ----> JPQL QUERY
	@Query(value = "select p from Product p where p.price between :min and :max order by p.name")
	List<Product> findProducts(@Param("min") BigDecimal min,@Param("max")BigDecimal max);
	
	
	//  USING -----> AGRIGATE FUNCTIONS IN QUERY
	
	@Query(value = "select count(*) from Product p where p.price between :min and :max")
	long countProducts(BigDecimal min,BigDecimal max);
	
	//PERFORM -----> UPDATE OPERATION
	@Modifying
	@Query(value = "update Product p set p.price=:newPrice where p.category.id=:categoryId")
	void updatePriceByCategory(@Param("newPrice")BigDecimal newPrice,@Param("categoryId")Byte categoryId);
	
//=======================================================================================================================
//                      PROJECTION (DTOS)    1.BY USING INTERFACE   2.BY USING DTO CLASS
//=======================================================================================================================
	//NOW WE FETCH PRODUCT BY CATEGORY
	//List<Product> findByCategory(Category category);
	//         |
	//          ---->NOW INSTED OF FETCHING UNNECARRY DETAILS I ONLY FETCH REQUIRED DATA(ONLY ID AND NAME  BY USING DTOS
	
/*-----------------------------------------------------------------------------------------------------------------------
	 *               BY USING INTERFACE(ProductSummary)
 ---------------------------------------------------------------------------------------------------------------------*/	
	//List<ProductSummary> findByCategory(Category category);
	/*                           |
	 *                           v
	 *        One confusion is arise ,hey we create interface of ProductSummary than who proved implementation
	 *        and what's going on under hood
	 *                            |
	 *                            v
	 *         | Repository Method: List<ProductSummary> findByCategory(category)
               |
               |----> Spring Data auto-generates:
               |        select p.id, p.name
               |        from Product p
               |        where p.category = :category
               |
               |----> Maps each result to:
               |        ProductSummary Proxy (with working getId()/getName())
               |
               |----> You can safely use: p.getId(), p.getName() 
               
	 * */
	// USING QUERY FOR DTOS =======> INTERFACE
//	@Query(value = "select p from Product p where p.category= :cate")//---->This select whole product details
	/*                      |
	 *                      v
	 *        ╔════════════════════════════════════╗
              ║   You select entity (p)            ║
              ╚════════════════════════════════════╝
                            │
                  DB Result: List<Product>
                            │
              Spring Data builds ProductSummary proxy(object)
                            │
          getId()/getName() → data from Product entity
	 * */
	
	@Query("select p.id, p.name from Product p where p.category= :cate")//=======> This method goes FAIL to fetching data
	/*                     |
	 *                     v
	 *   So confusion is arise, hey why this method returns null value for each fields of proxy object
	 *   we perform select query of two fields only insted of selecting whole object of Product
	 *   What's going on under hood 
	 *                     |
	 *                     v
	 *     | Repository Method: 
           |   @Query("select p.id, p.name ...")
           |   List<ProductSummary> fetchByCategory(category)
           |
           |----> Custom query returns:
           |       List<Object[]> (Each row is Object[] with id, name)
           |
           |----> But you asked for List<ProductSummary>!
           |
           |----> Spring tries to map Object[] to ProductSummary, fails
           |        (No constructor, no automatic mapping)
           |
           |----> Proxy fields are NOT mapped—getId() and getName() return NULL
           |
           |----> Output: ID--> null NAME--> null  
                          
                             |
                             |
                             V
	 *     ╔════════════════════════════════════╗
           ║   You select columns (p.id, p.name)║
           ╚════════════════════════════════════╝
                            │
                  DB Result: List<Object[]> ←───────┐
                            │                (Spring Data cannot convert array to interface)     
                    List<ProductSummary>   ←─────X──╯  
                            │
                  getId()/getName() → NULL
	 * */
	List<ProductSummary> fetchByCategory(@Param("cate")Category category);
	
//----------------------------------------------------------------------------------------------------------------------
//	               2. BY USING CLASSES(ProductSummaryDTO)
//----------------------------------------------------------------------------------------------------------------------	
	List<ProductSummaryDTO> findByCategory(Category category);
	
	//USING @QUERY IN DTOS CLASS
	@Query(value = "select p from Product p where p.category=:cate")
	//                     |
	//                     V
	//              Previously this query is not working but now its work with some updated like if class has public 
	//              getter and setter , and Noarg constructor but NOT RECOMENDED this approach
	List<ProductSummaryDTO> findByProductSummaryDTOs(@Param("cate")Category category);
	
	//Using @Query FOR ONLY SELECTION OF ID AND NAME
	//@Query(value = "select p.id,p.name from Product p where p.category=:cate")
	//                     |
	//                     V
	//              Previously this query is not working but now its work with some updated like if class has public 
	//              getter and setter , and Noarg constructor but NOT RECOMENDED this approach so using this----
	//                                                                                                          |
	//                                                                                <-------------------------
	@Query(value = "select new com.springboot.SpringBootJPA.dtos.ProductSummaryDTO(p.id,p.name) from Product p where p.category=:cate")
	List<ProductSummaryDTO> fetchByDTOClassUsingQuery(@Param("cate") Category category);
	
	
}
