package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;

import program.MainApp;
import program.models.Subject;
import program.repositories.SubjectRepository;

import java.io.IOException;
import java.util.*;

public class DashboardController {
    @FXML
    private TextField searchSectionField;

    @FXML
    private ListView<String> subjectListView;

    private SubjectRepository subjectRepository;
    private ObservableList<String> subjects;

    @FXML
    public void initialize() {
        subjectRepository = new SubjectRepository();

        subjects = FXCollections.observableArrayList();

        subjectListView.setItems(subjects);

        List<Subject> allSubjects = subjectRepository.getAllSubjects();
        updateSubjectListView(allSubjects);
    }

    public void updateSubjectListView(List<Subject> newSubjects) {
        subjects.clear();

        for (Subject subject : newSubjects) {
            subjects.add(subject.getSubjectName());
        }
    }

    public void createSection() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/createSection.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Create Section");
        stage.show();
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