package com.rezdy.lunch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rezdy.lunch.dto.RecipeDto;
import com.rezdy.lunch.service.LunchService;
import com.rezdy.lunch.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@Api(value="LUNCH API",description = "Rezdy lunch API system")
@RestController
@RequestMapping("/lunch")
@AllArgsConstructor
public class LunchController {
	
    private final LunchService lunchService;

    @ApiOperation(value = "For Criteria 1",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of images"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("")
    public List<RecipeDto> getRecipes(@RequestParam(value = "date") String date) {
    	
    	validateDateFormat(date);
    	return this.lunchService.getAllRecipesByDate(date);
    }
    
    @ApiOperation(value = "For Criteria 2",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of images"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/non-expired")
    public List<RecipeDto> getRecipesWithNonExpiredIngredients(@RequestParam(value = "date") String date){
    	validateDateFormat(date);
    	return this.lunchService.getNonExpiredRecipesOnDate(date);
    }
    
    @ApiOperation(value = "For Criteria 3",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of images"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/valid-recipes")
    public List<RecipeDto> getRecipesWithNonExpiredIngredients(@RequestParam(value = "date") String date,
    		@RequestParam(value = "bestBefore") String bestBefore){
    	validateDateFormat(date);
    	return this.lunchService.getAllRecipeWithSortedIngredients(date, bestBefore);
    }
    
    private void validateDateFormat(String date) {
		if(!DateUtil.isValid(date)) {
    		throw new RuntimeException("date is not valid!");
    	}
	}
    
}
