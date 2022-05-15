package com.sef.gui.controllers;

import com.sef.gui.GUI;
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

  @FXML
  public void handleLoginButtonAction() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username == null || username.isEmpty()) {
      loginMessage.setText("Please type in a username!");
      return;
    }

    if (password == null || password.isEmpty()) {
      loginMessage.setText("Password cannot be empty");
      return;
    }

    // TODO: Handle login

    if (username.equals("member") && password.equals("member")) {
      loginMessage.setText("Logged in as member!");
      return;
    }

    if (username.equals("admin") && password.equals("admin")) {
      loginMessage.setText("Logged in as admin!");
      return;
    }

    loginMessage.setText("Incorrect login!");
  }

  @FXML
  private void switchToRegister() throws IOException {
    GUI.setRoot("register");
  }
}
