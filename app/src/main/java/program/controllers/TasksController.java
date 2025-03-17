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

public class TasksController {
    @FXML
    private TextField searchTaskField;

    @FXML
    private ListView<String> taskListView;

    @FXML
    private Label deleteErrorLabel;

    private TaskRepository taskRepository;
    private ObservableList<String> tasks;

    private Section section;

    public void initialize(Section section) {
        this.section = section;
        taskRepository = new TaskRepository();
        tasks = FXCollections.observableArrayList();
        taskListView.setItems(tasks);
        List<Task> allTasks = taskRepository.getTasks(section);
        updateTaskListView(allTasks);
    }

    public void updateTaskListView(List<Task> allTasks) {
        tasks.clear();

        for(Task task : allTasks) {
            tasks.add(task.getName() + ": " + task.getDescription());
        }
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
        if(tasks.isEmpty()) {
            deleteErrorLabel.setText("There are no tasks to delete");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/deleteTask.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Delete Task");
        DeleteTaskController controller = loader.getController();
        controller.initialize(section);
        stage.show();
    }

    public void searchTasks() {
        String name = searchTaskField.getText().toLowerCase();
        List<Task> allTasks = taskRepository.getTasks(section);
        List<Task> filteredTasks = allTasks.stream().
                filter(task -> task.getName().toLowerCase().contains(name))
                .toList();
        updateTaskListView(filteredTasks);
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
