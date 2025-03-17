package program.controllers;

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

public class CreateTaskController {
    @FXML
    TextField taskName;

    @FXML
    TextField taskDescription;

    @FXML
    Label promptLabel;

    @FXML
    Button button;

    private TaskRepository taskRepository;
    private SectionRepository sectionRepository;
    private Section section;
    private List<Task> tasks;

    public void initialize(Section section) {
        taskRepository = new TaskRepository();
        sectionRepository = new SectionRepository();
        this.section = section;
        tasks = taskRepository.getTasks(section);
    }

    public void addTask() {
        if(taskName.getText().isEmpty()) {
            promptLabel.setText("Please enter task name");
            taskName.requestFocus();
            return;
        }

        Task task = new Task(section, taskName.getText(), taskDescription.getText());

        if(tasks.stream().anyMatch(t -> t.getName().equals(task.getName()))) {
            promptLabel.setText("Task already exists");
            taskName.requestFocus();
            return;
        }

        taskRepository.addTask(task);
        sectionRepository.addTask(section, task);
        tasks.add(task);
        promptLabel.setText("Task added");
        button.setText("Return to Tasks");
        taskName.clear();
        taskDescription.clear();
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
