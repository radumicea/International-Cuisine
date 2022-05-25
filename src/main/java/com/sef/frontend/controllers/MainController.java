package com.sef.frontend.controllers;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.models.RecipeModel;
import com.sef.frontend.GUI;
import com.sef.session.UserSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bson.types.ObjectId;

public class MainController implements Initializable {

  private static final int ENTRIES_PAGE = 3;

  private final RecipeController recipeController = new RecipeController();
  private final UserSession userSession = UserSession.getUserSession();

  private boolean isFavorites;
  private boolean isGlobal;

  private int favouritesFrom;
  private int favouritesTo;

  private int globalFrom;
  private int globalTo;

  private int localFrom;
  private int localTo;

  @FXML
  private Label mainLabel;

  @FXML
  private TableView<RecipeModel> recipeTable;

  @FXML
  private TableColumn<RecipeModel, ObjectId> recipeIdColumn;

  @FXML
  private TableColumn<RecipeModel, String> recipeNameColumn;

  @FXML
  private TableColumn<RecipeModel, String> countryColumn;

  @FXML
  private TableColumn<RecipeModel, String> descriptionColumn;

  @FXML
  private TableColumn<RecipeModel, String> tagsColumn;

  @FXML
  private TableColumn<RecipeModel, RecipeModel> imageColumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    recipeIdColumn.setCellValueFactory(new PropertyValueFactory<>("recipeId"));
    recipeNameColumn.setCellValueFactory(
      new PropertyValueFactory<>("recipeName")
    );

    countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

    descriptionColumn.setCellValueFactory(
      new PropertyValueFactory<>("description")
    );

    tagsColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));

    imageColumn.setCellValueFactory(cellData ->
      new SimpleObjectProperty<>(cellData.getValue())
    );
    imageColumn.setCellFactory(param -> {
      final ImageView imageView = new ImageView();
      imageView.setFitHeight(213.75);
      imageView.setFitWidth(285);
      TableCell<RecipeModel, RecipeModel> cell = new TableCell<>() {
        @Override
        public void updateItem(RecipeModel recipe, boolean empty) {
          String image;
          if (recipe == null) {
            return;
          }
          image = recipe.getImage();
          if (image == null || image.isEmpty()) {
            return;
          }
          imageView.setImage(
            new Image(
              new ByteArrayInputStream(Base64.getDecoder().decode(image))
            )
          );
        }
      };

      cell.setGraphic(imageView);
      return cell;
    });

    initializeGlobal();
  }

  private void initializeFavourites() {
    isFavorites = true;

    mainLabel.setText("FAVOURITE  RECIPES");

    favouritesFrom = 0;
    favouritesTo = ENTRIES_PAGE;
    updateFavouritesTable();
  }

  private void initializeGlobal() {
    isGlobal = true;
    isFavorites = false;

    mainLabel.setText("GLOBAL  RECIPES");

    globalFrom = 0;
    globalTo = ENTRIES_PAGE;
    updateGlobalTable();
  }

  private void initializeLocal() {
    isGlobal = false;
    isFavorites = false;

    mainLabel.setText("MY  RECIPES");

    localFrom = 0;
    localTo = ENTRIES_PAGE;
    updateLocalTable();
  }

  private final void updateFavouritesTable()
  {
    List<RecipeModel> favouriteRecipes = recipeController.getFavouriteFromToRecipes(
            userSession.userId,
            favouritesFrom,
            favouritesTo
    );
    if (favouriteRecipes == null) {
      recipeTable.getItems().clear();
      recipeTable.refresh();
      return;
    }
    favouritesFrom = favouritesTo;
    favouritesTo = favouritesFrom + ENTRIES_PAGE;

    recipeTable.getItems().clear();
    recipeTable.getItems().addAll(favouriteRecipes);
    recipeTable.refresh();
  }

  private final void updateGlobalTable() {
    List<RecipeModel> globalRecipes = recipeController.getFromToRecipes(
      globalFrom,
      globalTo
    );
    if (globalRecipes == null) {
      recipeTable.getItems().clear();
      recipeTable.refresh();
      return;
    }
    globalFrom = globalTo;
    globalTo = globalFrom + ENTRIES_PAGE;

    recipeTable.getItems().clear();
    recipeTable.getItems().addAll(globalRecipes);
    recipeTable.refresh();
  }

  private final void updateLocalTable() {
    List<RecipeModel> localRecipes = recipeController.getUserFromToRecipes(
      userSession.userId,
      localFrom,
      localTo
    );
    if (localRecipes == null) {
      recipeTable.getItems().clear();
      recipeTable.refresh();
      return;
    }
    localFrom = localTo;
    localTo = localFrom + ENTRIES_PAGE;

    recipeTable.getItems().clear();
    recipeTable.getItems().addAll(localRecipes);
    recipeTable.refresh();
  }

  @FXML
  public void handleNextButtonAction() {
    if (isFavorites)
    {
      updateFavouritesTable();
    }
    else
    {
      if (isGlobal) {
        updateGlobalTable();
      } else {
        updateLocalTable();
      }
    }
  }

  @FXML
  public void handleAddRecipeButtonAction() throws IOException {
    RecipeViewController.selectedRecipe = null;
    GUI.setRoot("recipe_view");
  }

  @FXML
  public void handleMyRecipesButtonAction() {
    recipeController.refresh();
    initializeLocal();
  }

  @FXML
  public void handleHomeButtonAction() {
    recipeController.refresh();
    initializeGlobal();
  }

  @FXML
  public void handleFavouritesButtonAction() {
    recipeController.refresh();
    initializeFavourites();
  }

  @FXML
  public void handleAddFavouritesButtonAction() {
    RecipeModel selectedRecipe = recipeTable
            .getSelectionModel()
            .getSelectedItem();

    if (selectedRecipe == null) {
      return;
    }
    recipeController.addFavouriteRecipe(selectedRecipe);
  }

  @FXML
  public void handleEditRecipeButtonAction() throws IOException {
    if (isGlobal) {
      return;
    }

    RecipeModel selectedRecipe = recipeTable
      .getSelectionModel()
      .getSelectedItem();

    if (selectedRecipe == null) {
      return;
    }

    RecipeViewController.selectedRecipe = selectedRecipe;
    GUI.setRoot("recipe_view");
  }
}
