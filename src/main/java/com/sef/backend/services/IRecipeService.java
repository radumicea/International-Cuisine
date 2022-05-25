package com.sef.backend.services;

import com.sef.backend.models.RecipeModel;
import java.util.List;
import org.bson.types.ObjectId;

public interface IRecipeService {
  int addRecipe(RecipeModel recipe);
  int updateRecipe(RecipeModel recipe);
  List<RecipeModel> getFromToRecipes(int from, int to);
  List<RecipeModel> getUserFromToRecipes(ObjectId userId, int from, int to);
  void refresh();
}
