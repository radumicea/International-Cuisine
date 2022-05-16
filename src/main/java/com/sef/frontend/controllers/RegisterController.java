package com.sef.frontend.controllers;

import com.sef.backend.services.UserService;
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

  private final UserService userService = new UserService();

  @FXML
  public void handleRegisterButtonAction() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String passwordConfirm = passwordConfirmField.getText();

    if (username == null || username.isEmpty()) {
      registerMessage.setText("Please type in a username!");
      return;
    }

    if (
      userService
        .getUsers()
        .stream()
        .filter(u -> u.username.equals(username))
        .findFirst()
        .isPresent()
    ) {
      registerMessage.setText("Username already exists!");
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

    if (!userService.registerUser(username, password)) {
      registerMessage.setText("Registration failed.");
    }

    registerMessage.setText("Registration was successful!");
  }

  @FXML
  private void switchToLogin() throws IOException {
    GUI.setRoot("login");
  }
}
