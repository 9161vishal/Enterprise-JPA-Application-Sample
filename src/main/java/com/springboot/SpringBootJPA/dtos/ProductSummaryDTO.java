package com.springboot.SpringBootJPA.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductSummaryDTO {
	public Long id;     //----------------------> here insted of method we can use fields
	public String name;
}
