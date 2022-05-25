package com.sef.frontend.controllers;

import com.sef.backend.controllers.UserController;
import com.sef.frontend.GUI;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {

  @FXML
  public Text registerMessage;

  @FXML
  public PasswordField passwordField;

  @FXML
  public PasswordField passwordConfirmField;

  @FXML
  public TextField usernameField;

  private final UserController userController = new UserController();

  @FXML
  public void handleRegisterButtonAction() throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String passwordConfirm = passwordConfirmField.getText();

    if (username == null || username.isEmpty()) {
      registerMessage.setText("Please type in a username!");
      return;
    }

    if (password == null || password.isEmpty()) {
      registerMessage.setText("Password cannot be empty.");
      return;
    }

    if (passwordConfirm == null || passwordConfirm.isEmpty()) {
      registerMessage.setText("Please confirm the password.");
      return;
    }

    if (!passwordConfirm.equals(password)) {
      registerMessage.setText("Passwords do not match!");
      return;
    }

    int registerStatus = userController.registerUser(username, password);

    if (registerStatus == -1) {
      registerMessage.setText("Registration failed.");
    } else if (registerStatus == 1) {
      registerMessage.setText("Username is already taken!");
    } else {
      registerMessage.setText("Registration was successful!");
      switchToLogin();
    }
  }

  @FXML
  private void switchToLogin() throws IOException {
    GUI.setRoot("login");
  }
}
