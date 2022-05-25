package com.sef;

import static org.junit.Assert.assertTrue;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.controllers.UserController;
import com.sef.backend.models.RecipeModel;
import com.sef.session.UserSession;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.Test;

public class TestBrowse {

  private final UserSession userSession = UserSession.getUserSession();
  private final UserController userController = new UserController();
  private final RecipeController recipeController = new RecipeController();

  private String username = "test";
  private String password = "t";

  @Test
  public void testBrowse() {
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
    List<RecipeModel> globalRecipes = recipeController.getFromToRecipes(0, 3);
    List<RecipeModel> localRecipes = recipeController.getUserFromToRecipes(
      userSession.userId,
      0,
      3
    );

    assertTrue(globalRecipes.size() > 0 && localRecipes.size() > 0);
  }
}
