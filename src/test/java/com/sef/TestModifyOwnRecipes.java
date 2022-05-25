package com.sef;

import static org.junit.Assert.assertTrue;

import java.util.List;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.controllers.UserController;
import com.sef.backend.models.RecipeModel;
import com.sef.session.UserSession;

import org.bson.types.ObjectId;
import org.junit.Test;

public class TestModifyOwnRecipes {
    
  private final UserSession userSession = UserSession.getUserSession();
  private final UserController userController = new UserController();
  private final RecipeController recipeController = new RecipeController();

  private String username = "test";
  private String password = "t";

  @Test
  public void testModify() {
    userController.logUserIn(username, password);
    RecipeModel testRecipe = new RecipeModel(
      new ObjectId(),
      "test",
      "test",
      "test",
      "test",
      "test"
    );
    recipeController.addRecipe(testRecipe);
    List<RecipeModel> localRecipes = recipeController.getUserFromToRecipes(
      userSession.userId,
      0,
      1
    );

    localRecipes.get(0).setDescription("Updated!");

    recipeController.updateRecipe(localRecipes.get(0));

    localRecipes = recipeController.getUserFromToRecipes(
      userSession.userId,
      0,
      1
    );

    assertTrue(localRecipes.get(0).getDescription().equals("Updated!"));
  }
}
