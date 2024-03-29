package com.recipe.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.recipe.commands.IngredientCommand;
import com.recipe.converters.IngredientCommandToIngredient;
import com.recipe.converters.IngredientToIngredientCommand;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Recipe;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	 private final IngredientToIngredientCommand ingredientToIngredientCommand;
	    private final IngredientCommandToIngredient ingredientCommandToIngredient;
	    private final RecipeRepository recipeRepository;
	    private final UnitOfMeasureRepository unitOfMeasureRepository;

	    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
	                                 IngredientCommandToIngredient ingredientCommandToIngredient,
	                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
	        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	        this.recipeRepository = recipeRepository;
	        this.unitOfMeasureRepository = unitOfMeasureRepository;
	    }



	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(!recipeOptional.isPresent()) {
			//implement error handling
			log.error("recipe id not found");
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommandOptional.isPresent()) {
			//todo implement error handling
			log.error("Ingredient id not found");
		}
		
		return ingredientCommandOptional.get();
	}

	
    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){
            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }
    }
}
