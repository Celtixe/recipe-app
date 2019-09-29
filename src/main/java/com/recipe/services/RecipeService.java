package com.recipe.services;

import java.util.Set;

import com.recipe.commands.RecipeCommand;
import com.recipe.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	
	Recipe findById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	RecipeCommand findCommandById(Long l);
	
	void deleteById(Long id);
}
