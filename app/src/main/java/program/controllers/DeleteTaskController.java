package program.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import program.MainApp;
import program.models.Section;
import program.models.Task;
import program.repositories.SectionRepository;
import program.repositories.TaskRepository;

import java.io.IOException;
import java.util.List;

public class DeleteTaskController {
    @FXML
    private ListView<String> taskListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button returnButton;

    @FXML
    private Label promptLabel;

    private Task task;
    private TaskRepository taskRepository;
    private Section section;
    private SectionRepository sectionRepository;
    private ObservableList<String> tasks;

    @FXML
    public void initialize(Section section) {
        this.section = section;

        taskRepository = new TaskRepository();
        sectionRepository = new SectionRepository();

        tasks = FXCollections.observableArrayList();
        taskListView.setItems(tasks);

        taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                deleteButton.setDisable(false);
                task = taskRepository.findByName(newValue);
            } else {
                deleteButton.setDisable(true);
                task = null;
            }
        });

        List<Task> allTasks = taskRepository.getTasks(section);
        updateTaskListView(allTasks);
    }


    public void updateTaskListView(List<Task> newTasks) {
        tasks.clear();

        for(Task task : newTasks) {
            this.tasks.add(task.getName() + ": " + task.getDescription());
        }
    }

    public void taskSelected(javafx.scene.input.MouseEvent event) {
        String taskInfo = taskListView.getSelectionModel().getSelectedItem();
        String[] info = taskInfo.split(": ");
        task = taskRepository.findByName(info[0]);
        System.out.println(task.getName());
    }

    public void deleteTask() {
        // Ask for confirmation before deletion
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Task");
        confirmationAlert.setHeaderText("Are you sure you want to delete this task?");
        confirmationAlert.setContentText("This action cannot be undone.");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                taskRepository.deleteTask(task);
                sectionRepository.deleteTask(section, task);

                taskListView.getSelectionModel().clearSelection();
                updateTaskListView(taskRepository.getTasks(section));

                returnButton.setText("Return to Tasks");
            }
        });
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
