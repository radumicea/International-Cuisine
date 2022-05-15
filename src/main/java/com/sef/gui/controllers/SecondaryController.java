package com.sef.gui.controllers;

import java.io.IOException;

import com.sef.gui.GUI;

import javafx.fxml.FXML;

public class SecondaryController
{
    @FXML
    private void switchToPrimary() throws IOException
    {
        GUI.setRoot("primary");
    }
}
