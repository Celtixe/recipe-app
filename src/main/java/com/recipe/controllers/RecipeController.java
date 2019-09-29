package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recipe.commands.RecipeCommand;
import com.recipe.services.RecipeService;

@Controller
public class RecipeController {
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/show/{id}")
	public String showById(@PathVariable Long id,Model m) {
		m.addAttribute("recipe",recipeService.findById(id));
		return "recipe/show";
	}
	
	@RequestMapping("recipe/new")
	public String newRecipe(Model m) {
		m.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeForm";
	}

	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedCommand.getId();
	}
}
