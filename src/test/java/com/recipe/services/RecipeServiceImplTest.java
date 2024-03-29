package com.recipe.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recipe.converters.RecipeCommandToRecipe;
import com.recipe.converters.RecipeToRecipeCommand;
import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeCommandToRecipe rc;
	@Mock
	RecipeToRecipeCommand cr;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository,rc,cr);
	}
	
	@Test
	public void getRecipes() throws Exception{
		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(recipe);
		
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes  = recipeService.getRecipes();
		assertEquals(recipes.size(),1);
		verify(recipeRepository,times(1)).findAll();
	}
}
