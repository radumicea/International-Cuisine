package com.sef.gui.controllers;

import com.sef.gui.GUI;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

  @FXML
  private void switchToPrimary() throws IOException {
    GUI.setRoot("primary");
  }
}
