package com.sef.frontend.controllers;

import com.sef.backend.controllers.UserController;
import com.sef.frontend.GUI;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

  @FXML
  public Text loginMessage;

  @FXML
  public PasswordField passwordField;

  @FXML
  public TextField usernameField;

  private final UserController userController = new UserController();

  @FXML
  public void handleLoginButtonAction() throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username == null || username.isEmpty()) {
      loginMessage.setText("Please type in a username!");
      return;
    }

    if (password == null || password.isEmpty()) {
      loginMessage.setText("Password cannot be empty.");
      return;
    }

    int loginStatus = userController.logUserIn(username, password);
    if (loginStatus == -1) {
      loginMessage.setText("Login failed!");
    } else if (loginStatus == 1) {
      loginMessage.setText("Incorrect username or password.");
    } else {
      loginMessage.setText("Successfully logged in!");
      GUI.setRoot("main");
    }
  }

  @FXML
  private void switchToRegister() throws IOException {
    GUI.setRoot("register");
  }
}
