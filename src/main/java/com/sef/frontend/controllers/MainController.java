package com.sef.frontend.controllers;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.models.RecipeModel;
import java.io.IOException;
import javafx.fxml.FXML;

// import com.sef.backend.models.Country;
// import com.sef.backend.models.RecipeModel;
// import java.util.List;
// import javafx.fxml.FXML;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.image.Image;

public class MainController {

  //   @FXML
  //   private TableView<RecipeModel> recipeTable;

  //   @FXML
  //   private TableColumn<RecipeModel, String> nameColumn;

  //   @FXML
  //   private TableColumn<RecipeModel, Country> CountryColumn;

  //   @FXML
  //   private TableColumn<RecipeModel, String> descriptionColumn;

  //   @FXML
  //   private TableColumn<RecipeModel, List<String>> tagsColumn;

  //   @FXML
  //   private TableColumn<RecipeModel, Image> imageColumn;

  private final RecipeController recipeController = new RecipeController();

  @FXML
  public void handleAddRecipeButtonAction() throws IOException {
    recipeController.addRecipe(
      new RecipeModel("name", "description", "country", "tags", "image")
    );
  }
}
