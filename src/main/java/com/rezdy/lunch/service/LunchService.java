package com.rezdy.lunch.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rezdy.lunch.dto.RecipeDto;
import com.rezdy.lunch.repository.RecipeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LunchService {


	private final RecipeRepository recipeRepository;
	
	/**
	 * Non expired recipes accordingly to the date supplied
	 * @param date
	 * @return
	 */
	
	public List<RecipeDto> getNonExpiredRecipesOnDate(final String date) {
		List<RecipeDto> result = null;
		try {
			result = recipeRepository.findAllRecipesWitValidDate(LocalDate.parse(date)).stream().map(recipe->{
				RecipeDto dto = RecipeDto.builder().build();
				dto.copy(recipe);
				return dto;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (null == result || result.size() == 0) {
			throw new RuntimeException("No Recipes are available for your selected Date " + date.toString());
		}

		return result;
	}


	/**
	 * Will return all recipes based on the param date.
	 * 
	 * @param date this is the useByDate
	 * @return
	 */
	
	public List<RecipeDto> getAllRecipesByDate(final String date) {
		List<RecipeDto> result = null;
		try {
			result = recipeRepository.findAllRecipesByDate(LocalDate.parse(date))
					.stream().map(recipe->{
						RecipeDto dto = RecipeDto.builder().build();
						dto.copy(recipe);
						return dto;
					}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (null == result || result.size() == 0) {
			throw new RuntimeException("No Recipes are available for your selected Date " + date.toString());
		}

		return result;
	}

	
	/**
	 * Recipes with Valid use by and sorted ingredients
	 * @param dateUseBy
	 * @param dateForBestBefore
	 * @return
	 */
	public List<RecipeDto> getAllRecipeWithSortedIngredients(String dateUseBy, String dateForBestBefore) {
		List<RecipeDto> result = null;
		try {
			LocalDate useBy = LocalDate.parse(dateUseBy);
			LocalDate bestBefore = LocalDate.parse(dateForBestBefore);
			result = recipeRepository.findAllRecipesWithValidUseByAndAfterBestBefore(useBy,bestBefore)
					.stream().map(recipe->{
						RecipeDto dto = RecipeDto.builder().build();
						dto.copy(recipe);
						return dto;
					}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (null == result || result.size() == 0) {
			throw new RuntimeException("No Recipes are available for your selected Date");
		}

		return result;
	}

}
