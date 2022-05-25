package com.sef;

import static org.junit.Assert.assertTrue;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.controllers.UserController;
import com.sef.backend.models.RecipeModel;
import org.bson.types.ObjectId;
import org.junit.Test;

public class TestAddRecipe {

  private final UserController userController = new UserController();
  private final RecipeController recipeController = new RecipeController();

  private String username = "test";
  private String password = "t";

  @Test
  public void testRegisterLogin() {
    userController.logUserIn(username, password);
    assertTrue(
      recipeController.addRecipe(
        new RecipeModel(new ObjectId(), "test", "test", "test", "test", "test")
      ) ==
      0
    );
  }
}
