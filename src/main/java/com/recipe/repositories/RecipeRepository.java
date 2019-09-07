package com.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
