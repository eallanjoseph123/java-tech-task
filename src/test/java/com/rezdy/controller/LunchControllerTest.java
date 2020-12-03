package com.rezdy.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rezdy.lunch.controller.LunchController;
import com.rezdy.lunch.dto.IngredientsDto;
import com.rezdy.lunch.dto.RecipeDto;
import com.rezdy.lunch.service.LunchService;

@RunWith(MockitoJUnitRunner.class)
public class LunchControllerTest {

    private static String ROOT_MAP = "/lunch";

    @InjectMocks
    private LunchController lunchController;

    @Mock
    private LunchService lunchService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(lunchController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getRecipes_shouldReturnAllRecipesByDate() throws Exception {
        String url = ROOT_MAP;

        String date = "2030-01-01";

        List<RecipeDto> mockData = new ArrayList<>();

        List<IngredientsDto> ingredients = new ArrayList<>();
        ingredients.add(IngredientsDto.builder().title("i1").expiryDate(LocalDate.parse(date)).build());

        List<IngredientsDto> ingredients2 = new ArrayList<>();
        ingredients2.add(IngredientsDto.builder().title("i2").expiryDate(LocalDate.parse("2030-01-02")).build());

        List<IngredientsDto> ingredients3 = new ArrayList<>();
        ingredients3.add(IngredientsDto.builder().title("i3").expiryDate(LocalDate.parse("2030-01-04")).build());

        mockData.add(RecipeDto.builder().title("recipe1").ingredients(ingredients).build());

        mockData.add(RecipeDto.builder().title("recipe2").ingredients(ingredients2).build());
        mockData.add(RecipeDto.builder().title("recipe3").ingredients(ingredients3).build());

        when(this.lunchService.getAllRecipesByDate(date)).thenReturn(mockData);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url)
                .param("date", date);

        mockMvc.perform(getRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath(".title").exists())
                .andExpect(jsonPath(".ingredients").exists())
                .andDo(MockMvcResultHandlers.print()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test(expected = Exception.class)
    public void getRecipes_shouldReturnExceptionIfDateIsNotValid() throws Exception {
        String url = ROOT_MAP;

        String date = "2030-01-01as";


        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url)
                .param("date", date);

        mockMvc.perform(getRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
    
    @Test(expected = Exception.class)
    public void getRecipes_shouldReturnExceptionIfDateIsNotValidDueToFormat() throws Exception {
        String url = ROOT_MAP;

        String date = "2030/01/01";


        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url)
                .param("date", date);

        mockMvc.perform(getRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void getRecipesWithNonExpiredIngredients_shouldReturnAllRecipesByDate() throws Exception {
        String url = ROOT_MAP+"/non-expired";

        String date = "2030-01-01";

        List<RecipeDto> mockData = new ArrayList<>();

        List<IngredientsDto> ingredients = new ArrayList<>();
        ingredients.add(IngredientsDto.builder().title("i1").expiryDate(LocalDate.parse(date)).build());

        List<IngredientsDto> ingredients2 = new ArrayList<>();
        ingredients2.add(IngredientsDto.builder().title("i2").expiryDate(LocalDate.parse("2030-01-02")).build());

        List<IngredientsDto> ingredients3 = new ArrayList<>();
        ingredients3.add(IngredientsDto.builder().title("i3").expiryDate(LocalDate.parse("2030-01-04")).build());

        mockData.add(RecipeDto.builder().title("recipe1").ingredients(ingredients).build());

        mockData.add(RecipeDto.builder().title("recipe2").ingredients(ingredients2).build());
        mockData.add(RecipeDto.builder().title("recipe3").ingredients(ingredients3).build());

        when(this.lunchService.getNonExpiredRecipesOnDate(date)).thenReturn(mockData);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url)
                .param("date", date);

        mockMvc.perform(getRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath(".title").exists())
                .andDo(MockMvcResultHandlers.print()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
    }
    
    @Test
    public void recipesWithValidUseBy_shouldReturnAllRecipesWithValidUseByDate() throws Exception {
        String url = ROOT_MAP+"/valid-recipes";

        String date = "2030-01-01";

        List<RecipeDto> mockData = new ArrayList<>();

        List<IngredientsDto> ingredients = new ArrayList<>();
        ingredients.add(IngredientsDto.builder().title("i1").expiryDate(LocalDate.parse(date)).build());

        List<IngredientsDto> ingredients2 = new ArrayList<>();
        ingredients2.add(IngredientsDto.builder().title("i2").expiryDate(LocalDate.parse("2030-01-02")).build());

        List<IngredientsDto> ingredients3 = new ArrayList<>();
        ingredients3.add(IngredientsDto.builder().title("i3").expiryDate(LocalDate.parse("2030-01-04")).build());

        mockData.add(RecipeDto.builder().title("recipe1").ingredients(ingredients).build());

        mockData.add(RecipeDto.builder().title("recipe2").ingredients(ingredients2).build());
        mockData.add(RecipeDto.builder().title("recipe3").ingredients(ingredients3).build());

        String dateUseBy = "2030-01-01";

        String dateForBestBefore = "2030-01-01";

        when(this.lunchService.getAllRecipeWithSortedIngredients(dateUseBy,dateForBestBefore)).thenReturn(mockData);

        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get(url)
                .param("date", dateUseBy)
                .param("bestBefore", dateForBestBefore);

        mockMvc.perform(getRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)))
                .andExpect(jsonPath(".title").exists())
                .andDo(MockMvcResultHandlers.print()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful());
    }

}
