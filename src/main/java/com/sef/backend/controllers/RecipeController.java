package com.sef.backend.controllers;

import com.sef.backend.models.RecipeModel;
import com.sef.backend.services.IRecipeService;
import com.sef.backend.services.RecipeService;
import java.util.List;
import org.bson.types.ObjectId;

public class RecipeController {

  private final IRecipeService recipeService = new RecipeService();

  public int addRecipe(RecipeModel recipe) {
    return recipeService.addRecipe(recipe);
  }

  public List<RecipeModel> getFromToRecipes(int from, int to) {
    return recipeService.getFromToRecipes(from, to);
  }

  public List<RecipeModel> getUserFromToRecipes(
    ObjectId userId,
    int from,
    int to
  ) {
    return recipeService.getUserFromToRecipes(userId, from, to);
  }
}
