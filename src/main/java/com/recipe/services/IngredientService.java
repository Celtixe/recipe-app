package com.recipe.services;

import com.recipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
}
