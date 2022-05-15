package com.sef.gui.controllers;

import com.sef.gui.GUI;
import java.io.IOException;
import javafx.fxml.FXML;

public class RegisterController {

  @FXML
  private void switchToSecondary() throws IOException {
    GUI.setRoot("register");
  }
}
