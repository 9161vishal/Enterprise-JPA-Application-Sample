# Enterprise JPA Application Sample

This project is an enterprise-grade Spring Boot application for mastering Spring Data JPA in real-world scenarios. It demonstrates advanced repository design, dynamic queries, DTO projections, and service orchestration. Ideal for interview preparation, hands-on concept reinforcement, or code walkthroughs.

---

## Features

- Full CRUD operations for entities: User, Product, Category, Address, Tag, Profile
- Advanced Spring Data repository patterns: JpaRepository, Criteria API, Specifications, and custom queries
- Dynamic querying: Example API, Criteria, and Specification-based filtering
- DTO projections using both interfaces and classes for optimized results
- Complex entity relationships: OneToOne, OneToMany, ManyToOne, cascading, orphan removal
- Sorting and pagination for scalable data management
- Transactional service layer orchestration

---

## Technology Stack

- Java 17+
- Spring Boot 
- Spring Data JPA
- Hibernate
- Lombok
- H2/Any SQL Database

---

## Project Structure


```text
src/
└── main/
    └── java/
        └── com/
            └── springboot/
                └── SpringBootJPA/
                    ├── application/      -> Main Spring Boot application class
                    ├── entities/         -> Entity classes (User, Product, Category, Address, Profile, Tag)
                    ├── dtos/             -> DTO interfaces & classes
                    ├── repositories/     -> JpaRepository and custom repositories
                    ├── services/         -> Business/service logic
                    ├── specification/    -> Criteria and Specification classes
                    
                    
```


## Key Classes & Repositories (Major Functionalities)

### UserService
Handles user-centric operations.
- User deletion and address management.
- Wishlist add/remove functionality.
- Dynamic queries (find by email, find all with EntityGraph for eager fetch).
- Profile association and transactional boundaries.

### ProductService
Central for product management.
- Dynamic queries using Example entity, ExampleMatcher, and null value handling.
- Criteria API usage for advanced queries.
- Specification API for complex filtering (by name, price range, etc.).
- Paging, sorting, and aggregation (count, grouped queries).
- Data projection into DTOs (interface and class-based).

### ProfileService
Serves user profile features.
- Fetch based on loyalty points.
- Profiles retrieval and custom DTO mapping.
- Sorted fetches and stream-based post-processing.

### ProductRepository & UserRepository
Spring Data JPA repositories with enhanced custom methods.
- Finders by price, category, and user attributes.
- JPQL and criteria queries.
- Support for projection and join queries.

### ProductSpecification (and specifications package)
Contains ready-to-use Specification predicates for complex search.
- `hasName`, `lessThanOrEqualTo`, `greaterThanOrEqualTo` for flexible filtering.

### DTOs Package
Defines projection interfaces/classes for efficient data transfer and presentation, decoupling entities from API/service contracts.

---

## How to Run

1. Configure your database connection in `application.properties`
2. Build and run the Spring Boot application from your IDE or using Maven/Gradle
3. All features can be tested by invoking service layer methods

---

## Learning Highlights

- Enterprise repository/query architecture and modular code organization
- DTO mapping and efficient data projection strategies
- Complex entity relationships and transactional management
- Usage of dynamic and runtime queries for scalable systems
- Service-oriented design best practices for production-ready code

---

## Author

Developed by Vishal Chaudhary  
Contact: 9161vishal@gmail.com


