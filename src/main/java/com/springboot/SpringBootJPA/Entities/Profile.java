package com.springboot.SpringBootJPA.Entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "profiles")
public class Profile {
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="bio")
	private String bio;
	
	@Column(name="phone_number")
	private Long phoneNumber;
	
	@Column(name="date_of_birth")
	private LocalDate date_of_birthDate;
	
	@Column(name="loyalty_points")
	private Integer loyeltyPoints;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	@MapsId  //-----> this tells to hibernate this id join column both pk as well as fk
	@ToString.Exclude
	private User user;
}
