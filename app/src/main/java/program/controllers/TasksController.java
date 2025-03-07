package program.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import program.MainApp;

import java.io.IOException;

public class TasksController {

    public void createTask() throws IOException {
    }

    public void deleteTask() throws IOException {
    }

    public void searchTasks() {
    }

    public void taskSelected() {

    }

    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/sections.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Sections");
        stage.show();
    }

    public void close() {
        Stage stage = MainApp.getPrimaryStage();
        stage.close();
    }
}
