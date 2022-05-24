package com.sef.frontend.controllers;

import com.sef.backend.controllers.RecipeController;
import com.sef.backend.models.RecipeModel;
import com.sef.frontend.GUI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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

  private static final long _512KB = 512 * 1024;

  private final RecipeController recipeController = new RecipeController();

  @FXML
  private Text message;

  @FXML
  private TextField nameField;

  @FXML
  private TextField countryField;

  @FXML
  private TextField descriptionField;

  @FXML
  private TextField tagsField;

  private String image = "";

  @FXML
  public void handleAddRecipeButtonAction() throws IOException {
    GUI.setRoot("recipe_view");
  }

  @FXML
  public void handleSaveButtonAction() throws IOException {
    recipeController.addRecipe(
      new RecipeModel(
        nameField.getText(),
        descriptionField.getText(),
        countryField.getText(),
        tagsField.getText(),
        image
      )
    );

    image = "";
  }

  @FXML
  void handleCancelButtonAction() throws IOException {
    GUI.setRoot("main");
  }

  @FXML
  void handleChooseImageButtonAction() throws IOException {
    FileChooser fc = new FileChooser();
    fc.setTitle("Open Image");
    fc
      .getExtensionFilters()
      .addAll(
        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
        new ExtensionFilter("All Files", "*.*")
      );
    File file = fc.showOpenDialog(GUI.stage);
    if (file == null) {
      return;
    }

    if (file.length() > _512KB) {
      message.setText("Image size exceeds 512KB!");
      return;
    }

    image =
      Base64
        .getEncoder()
        .encodeToString(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
  }
}
