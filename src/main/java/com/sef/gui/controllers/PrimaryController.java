package com.sef.gui.controllers;

import java.io.IOException;

import com.sef.gui.GUI;

import javafx.fxml.FXML;

public class PrimaryController
{
    @FXML
    private void switchToSecondary() throws IOException
    {
        GUI.setRoot("secondary");
    }
}
