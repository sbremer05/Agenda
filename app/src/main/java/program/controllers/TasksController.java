package program.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import program.MainApp;
import program.models.Section;

import java.io.IOException;

public class TasksController {

    private Section section;

    public void initialize(Section section) {
        this.section = section;
    }

    public void createTask() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/createTask.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Create Task");
        stage.show();
        CreateTaskController controller = loader.getController();
        controller.initialize(section);
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
