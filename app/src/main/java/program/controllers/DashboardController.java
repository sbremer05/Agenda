package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.*;
import program.MainApp;

import java.io.IOException;
import java.util.*;

public class DashboardController {
    @FXML
    TextField searchSectionField;

    public void addSection() {

    }

    public void searchSections() {

    }

    public void courseSelected() {

    }

    public void close() {
        var stage = MainApp.getPrimaryStage();
        stage.close();
    }
}