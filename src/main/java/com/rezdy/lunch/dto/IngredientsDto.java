package com.rezdy.lunch.dto;

import java.time.LocalDate;

import com.rezdy.lunch.entity.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IngredientsDto implements DtoCloner<Ingredient>{
	
	private String title;
	
	private LocalDate expiryDate;
	
	private LocalDate bestBefore;
	
	public void copy(Ingredient ingredient) {
		this.setTitle(ingredient.getTitle());
		this.setExpiryDate(ingredient.getUseBy());
		this.setBestBefore(ingredient.getBestBefore());
	}
}
