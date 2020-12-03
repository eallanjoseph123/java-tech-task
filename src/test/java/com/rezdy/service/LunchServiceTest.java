package com.rezdy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.rezdy.lunch.dto.RecipeDto;
import com.rezdy.lunch.entity.Ingredient;
import com.rezdy.lunch.entity.Recipe;
import com.rezdy.lunch.repository.RecipeRepository;
import com.rezdy.lunch.service.LunchService;

@RunWith(MockitoJUnitRunner.class)
public class LunchServiceTest {

	@InjectMocks
	private LunchService lunchService;

	@Mock
	private RecipeRepository recipeDao;

	@Before
	public void setUp() {

	}

	@Test
	public void getAllRecipesByDate_shouldReturnAllRecipesBasedOnTheUseByDate() {

		String date = "2030-01-01";

		LocalDate expiryDate = LocalDate.parse(date);

		Set<Ingredient> ingredients1 = new HashSet<>();
		ingredients1.add(Ingredient.builder().title("chicken").useBy(LocalDate.parse(date)).build());

		
		List<Recipe> mockData = new ArrayList<>();
		mockData.add(Recipe.builder()
				.title("chicken soup")
				.ingredients(ingredients1)
				.build());
		
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(Ingredient.builder().title("bread").useBy(LocalDate.parse(date)).build());

		mockData.add(Recipe.builder()
				.title("burger")
				.ingredients(ingredients)
				.build());
		when(this.recipeDao.findAllRecipesByDate(expiryDate)).thenReturn(mockData);

		List<RecipeDto> result = this.lunchService.getAllRecipesByDate(date);

		verify(recipeDao, times(1)).findAllRecipesByDate(expiryDate);
		assertEquals(2, result.size());

	}

	@Test(expected = RuntimeException.class)
	public void getAllRecipesByDate_shouldReturnExceptionIfNoAvailableRecipes() {

		String date = "2030-01-01";

		LocalDate expiryDate = LocalDate.parse(date);

		List<Recipe> mockData = new ArrayList<>();
		when(this.recipeDao.findAllRecipesByDate(expiryDate)).thenReturn(mockData);

		this.lunchService.getAllRecipesByDate(date);

		verify(recipeDao, times(1)).findAllRecipesByDate(expiryDate);

	}

	@Test
	public void getAllRecipesWithValidDate_shouldReturnAllRecipesWithTheValidateDate() {

		String date = "2030-01-01";

		LocalDate expiryDate = LocalDate.parse(date);

		Set<Ingredient> ingredients1 = new HashSet<>();
		ingredients1.add(Ingredient.builder().title("chicken").useBy(LocalDate.parse(date)).build());

		
		List<Recipe> mockData = new ArrayList<>();
		mockData.add(Recipe.builder()
				.title("chicken soup")
				.ingredients(ingredients1)
				.build());
		
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(Ingredient.builder().title("bread").useBy(LocalDate.parse(date)).build());

		mockData.add(Recipe.builder()
				.title("burger")
				.ingredients(ingredients)
				.build());
		when(this.recipeDao.findAllRecipesWitValidDate(expiryDate)).thenReturn(mockData);

		List<RecipeDto> result = this.lunchService.getNonExpiredRecipesOnDate(date);

		verify(recipeDao, times(1)).findAllRecipesWitValidDate(expiryDate);
		assertEquals(2, result.size());
		
	}
	
	@Test
	public void getAllRecipeWithSortedIngredients_shouldReturnAllRecipeWithValidUseBy() {
		
		String dateUseBy = "2030-01-01";

		LocalDate useBy = LocalDate.parse(dateUseBy);
		
		String dateForBestBefore = "2030-01-01";

		LocalDate bestBefore = LocalDate.parse(dateForBestBefore);

		Set<Ingredient> ingredients1 = new HashSet<>();
		ingredients1.add(Ingredient.builder().title("chicken").useBy(LocalDate.parse(dateUseBy)).build());

		
		List<Recipe> mockData = new ArrayList<>();
		mockData.add(Recipe.builder()
				.title("chicken soup")
				.ingredients(ingredients1)
				.build());
		
		Set<Ingredient> ingredients = new HashSet<>();
		ingredients.add(Ingredient.builder().title("bread").useBy(LocalDate.parse(dateUseBy)).build());

		mockData.add(Recipe.builder()
				.title("burger")
				.ingredients(ingredients)
				.build());
		when(this.recipeDao.findAllRecipesWithValidUseByAndAfterBestBefore(useBy,bestBefore)).thenReturn(mockData);
		

		
		List<RecipeDto> result = this.lunchService.getAllRecipeWithSortedIngredients(dateUseBy,dateForBestBefore);
		
		assertEquals(2, result.size());
	}
	
	
	
	
	

}
