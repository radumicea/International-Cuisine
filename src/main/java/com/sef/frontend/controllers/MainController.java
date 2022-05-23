package com.sef.frontend.controllers;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.models.RecipeModel;
import com.sef.frontend.GUI;
import com.sef.session.UserSession;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class MainController implements Initializable {

  private final RecipeController recipeController = new RecipeController();
  private final UserSession userSession = UserSession.getUserSession();

  private boolean isGlobal;

  private int globalFrom;
  private int globalTo;

  private int localFrom;
  private int localTo;

  @FXML
  private TableView<RecipeModel> recipeTable;

  @FXML
  private TableColumn<RecipeModel, String> recipeNameColumn;

  @FXML
  private TableColumn<RecipeModel, String> countryColumn;

  @FXML
  private TableColumn<RecipeModel, String> descriptionColumn;

  @FXML
  private TableColumn<RecipeModel, List<String>> tagsColumn;

  @FXML
  private TableColumn<RecipeModel, Image> imageColumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    recipeNameColumn.setCellValueFactory(
      new PropertyValueFactory<>("recipeName")
    );

    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

    descriptionColumn.setCellValueFactory(
      new PropertyValueFactory<>("description")
    );

    tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

    imageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));

    initializeGlobal();
  }

  private void initializeGlobal() {
    isGlobal = true;

    globalFrom = 0;
    globalTo = 5;
    updateGlobalTable();
  }

  private void initializeLocal() {
    isGlobal = false;

    localFrom = 0;
    localTo = 5;
    updateLocalTable();
  }

  private final void updateGlobalTable() {
    List<RecipeModel> globalRecipes = recipeController.getFromToRecipes(
      globalFrom,
      globalTo
    );
    globalFrom = globalTo;
    globalTo = globalFrom + 5;

    System.out.println(globalRecipes);

    recipeTable.getItems().clear();
    recipeTable.getItems().addAll(globalRecipes);
  }

  private final void updateLocalTable() {
    List<RecipeModel> localRecipes = recipeController.getUserFromToRecipes(
      userSession.userId,
      localFrom,
      localTo
    );
    if (localRecipes == null) {
      return;
    }
    localFrom = localTo;
    localTo = localFrom + 5;

    System.out.println(localRecipes);

    recipeTable.getItems().clear();
    recipeTable.getItems().addAll(localRecipes);
  }

  @FXML
  public void handleNextButtonAction() {
    if (isGlobal) {
      updateGlobalTable();
    } else {
      updateLocalTable();
    }
  }

  @FXML
  public void handleAddRecipeButtonAction() throws IOException {
    GUI.setRoot("recipe_view");
  }

  @FXML
  public void handleMyRecipesButtonAction() {
    initializeLocal();
  }

  @FXML
  public void handleHomeButtonAction() {
    initialize(null, null);
  }
}
