package com.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.recipe.domain.Category;
import com.recipe.domain.Difficulty;
import com.recipe.domain.Ingredient;
import com.recipe.domain.Notes;
import com.recipe.domain.Recipe;
import com.recipe.domain.UnitOfMeasure;
import com.recipe.repositories.CategoryRepository;
import com.recipe.repositories.RecipeRepository;
import com.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	
	public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loading bootstrap data");
		
	}
	
	private List<Recipe> getRecipes(){
		List<Recipe> recipes = new ArrayList<>(2);
		
		//get UOMs
		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
		
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		if(!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		if(!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
		
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
		
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
		
		if(!pinchUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");
		
		if(!ounceUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM Not Found");
		}
		
		//get optionals uoms
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure pinchUom = pinchUomOptional.get();
		UnitOfMeasure ounceUom = ounceUomOptional.get();
		
		//get categories
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
		
		if(!italianCategoryOptional.isPresent()) {
			throw new RuntimeException("Expected Category Not Found");
		}
		
		//get optionals
		Category americanCategory = americanCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		Category italianCategory = italianCategoryOptional.get();

		//Yummy Guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setCookTime(20);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed.\n"
				+ " Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.\n"
				+ " Place in a bowl.\n" + "2 Mash with a fork: Using a fork, roughly mash the avocado.\n"
				+ " (Don't overdo it! The guacamole should be a little chunky.)\n" +
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice.\n"
				+ "The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.");
		
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.");
		
		guacRecipe.setNotes(guacNotes);
		guacNotes.setRecipe(guacRecipe);
		
		
		guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(.5), teaSpoonUom));
		guacRecipe.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("serrano chilies", new BigDecimal(2), eachUom));
		guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
		guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
		guacRecipe.addIngredient(new Ingredient("ripe tomato", new BigDecimal(.5), eachUom));

		
		guacRecipe.getCategories().add(mexicanCategory);
		guacRecipe.getCategories().add(americanCategory);
		
		recipes.add(guacRecipe);
		
		//Yummy tacos
		Recipe tacosRecipe = new Recipe();
		tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacosRecipe.setCookTime(9);
		tacosRecipe.setPrepTime(20);
		tacosRecipe.setDifficulty(Difficulty.MODERATE);
		
		tacosRecipe.setDirections("1 Heat the pan, prep the ingredients: Heat up the cast iron frying pan on medium high heat.\n"
				+ " While the pan is heating, prepare your ingredients. Cut some slices of cheese."
				+ "2 Butter a tortilla and heat it in the pan until it bubbles\n"
				+ "3 Add cheese: Add slices of cheese to one side of the tortilla.\n"
				+ " Make sure to allow room for the cheese to melt and not spread all over the pan.");
		
		Notes tacosNotes = new Notes();
		tacosNotes.setRecipeNotes("This method makes delicious, buttery, cheesey tacos. If you are avoiding butter, or frying oil (which you could also use), you could make these in a microwave.\n"
				+ " Soften the tortillas first in the microwave. We use 20 seconds on high per tortilla, with the tortillas sitting on a paper towel in the microwave to absorb moisture.\n"
				+ " Once they've been softened this way you can add cheese and fold them over and heat them a few seconds more, just until the cheese melts.");
		tacosNotes.setRecipe(tacosRecipe);
		tacosRecipe.setNotes(tacosNotes);
		
		tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder",new BigDecimal(2),tableSpoonUom));
		tacosRecipe.addIngredient(new Ingredient("Dried Oregano",new BigDecimal(1),teaSpoonUom));
		tacosRecipe.addIngredient(new Ingredient("Dried Cumin",new BigDecimal(1),teaSpoonUom));
		tacosRecipe.addIngredient(new Ingredient("Sugar",new BigDecimal(1),teaSpoonUom));
		tacosRecipe.addIngredient(new Ingredient("Salt",new BigDecimal(0.5),teaSpoonUom));
		tacosRecipe.addIngredient(new Ingredient("Packed baby argula",new BigDecimal(3),cupUom));

		tacosRecipe.getCategories().add(americanCategory);
		tacosRecipe.getCategories().add(mexicanCategory);
		
		recipes.add(tacosRecipe);
		
		
		
		
		
		return recipes;
	}

	
	
}
