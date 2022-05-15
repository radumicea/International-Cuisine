package com.sef.controllers;

import com.sef.App;
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

  @FXML
  public void handleRegisterButtonAction() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String passwordConfirm = passwordConfirmField.getText();

    if (username == null || username.isEmpty()) {
      registerMessage.setText("Please type in a username!");
      return;
    }

    if (password == null || password.isEmpty()) {
      registerMessage.setText("Password cannot be empty");
      return;
    }

    if (passwordConfirm == null || passwordConfirm.isEmpty()) {
      registerMessage.setText("Please confirm the password");
      return;
    }

    if (!passwordConfirm.equals(password)) {
      registerMessage.setText("Passwords do not match!");
      return;
    }

    // TODO: unique user names and handle logins

    registerMessage.setText("Registration was successful!");
  }

  @FXML
  private void switchToLogin() throws IOException {
    App.setRoot("login");
  }
}
