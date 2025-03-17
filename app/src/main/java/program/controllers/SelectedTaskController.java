package program.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import program.MainApp;
import program.models.Section;
import program.models.Task;
import program.repositories.TaskRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectedTaskController {
    @FXML
    Label taskName;

    @FXML
    Label taskDescription;

    private TaskRepository taskRepository;
    private Task task;
    private Section section;

    public void initialize(Section section, Task task) {
        this.section = section;
        this.task = task;
        taskRepository = new TaskRepository();
        taskName.setText(task.getName());
        taskDescription.setText(task.getDescription());
    }

    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/tasks.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle(section.getName() + " Tasks");
        TasksController controller = loader.getController();
        controller.initialize(section);
        stage.show();
    }
}
