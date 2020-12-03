package com.rezdy.lunch.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.rezdy.lunch.entity.Ingredient;
import com.rezdy.lunch.entity.Recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecipeDto implements DtoCloner<Recipe> {

	private String title;

	private List<IngredientsDto> ingredients;

	public void copy(final Recipe recipe) {
		this.setTitle(recipe.getTitle());
		
		// TODO: NEED TO fix the Stackoverflow issue due to hibernate/jpa configuration
		
		/*List<Ingredient> ingredients = new ArrayList<>(recipe.getIngredients());
		Collections.sort(ingredients);

		

		this.ingredients = ingredients.stream().map(ingredient -> {
			IngredientsDto dto = IngredientsDto.builder().build();
			dto.copy(ingredient);
			return dto;
		}).collect(Collectors.toList());*/

	}
}
