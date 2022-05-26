package com.sef.frontend.controllers;

import com.sef.backend.controllers.ReportController;
import com.sef.backend.models.RecipeModel;
import com.sef.backend.models.ReportModel;
import com.sef.frontend.GUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportViewController implements Initializable {

    private ReportController reportController = new ReportController();

    @FXML
    private Text message;

    @FXML
    private TextField reasonField;

    @FXML
    private Button sendButton;

    static RecipeModel selectedRecipe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (selectedRecipe == null)
            return;
    }

    @FXML
    public void handleSendReportButtonAction() throws IOException {
        String reasonFieldText = reasonField.getText();
        if (reasonFieldText == null || reasonFieldText.isEmpty()) {
            message.setText("Field can not be empty!");
            return;
        }
        reportController.addReport(new ReportModel(selectedRecipe, reasonFieldText));

        GUI.setRoot("main");
    }

}
