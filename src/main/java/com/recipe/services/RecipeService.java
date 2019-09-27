package com.recipe.services;

import java.util.Set;

import com.recipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
}
