package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
}
