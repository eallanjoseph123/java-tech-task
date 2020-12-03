package com.rezdy.lunch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rezdy.lunch.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String>{
	
	@Query(value="SELECT DISTINCT r FROM Recipe r inner join r.ingredients i where i.useBy > :useBy ")
	List<Recipe> findAllRecipesByDate( @Param("useBy") LocalDate  useBy);
	
	@Query(value="SELECT DISTINCT r FROM Recipe r inner join r.ingredients i where i.useBy > :useBy ")
	List<Recipe> findAllRecipesWitValidDate( @Param("useBy") LocalDate  useBy);
	
	@Query(value="SELECT DISTINCT r FROM Recipe r inner join r.ingredients i where i.useBy <= :useBy and i.bestBefore >  :bestBefore")
	List<Recipe> findAllRecipesWithValidUseByAndAfterBestBefore( @Param("useBy") LocalDate  useBy,@Param("bestBefore") LocalDate  bestBefore);
}
