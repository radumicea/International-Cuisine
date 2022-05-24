package com.sef.backend.controllers;

import com.sef.backend.models.RecipeModel;
import com.sef.backend.services.IRecipeService;
import com.sef.backend.services.RecipeService;

public class RecipeController {

  private final IRecipeService recipeService = new RecipeService();

  public int addRecipe(RecipeModel recipe) {
    return recipeService.addRecipe(recipe);
  }
}
