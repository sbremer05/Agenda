package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import program.MainApp;

import java.io.IOException;
import java.util.List;

public class CreateSectionController {
    @FXML
    TextField newSubject;

    public void addSubject() {

    }

    public void cancel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/dashboard.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
