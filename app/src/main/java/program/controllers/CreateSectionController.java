package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import program.MainApp;
import program.models.Subject;
import program.repositories.SubjectRepository;

import java.io.IOException;
import java.util.List;

public class CreateSectionController {
    @FXML
    TextField newSubject;

    @FXML
    Label promptLabel;

    @FXML
    Button button;

    private List<Subject> subjects;
    private SubjectRepository subjectRepository;

    @FXML
    public void initialize() {
        subjectRepository = new SubjectRepository();
        subjects = subjectRepository.getAllSubjects();
    }

    public void addSubject() {
        if(newSubject.getText().isEmpty()) {
            promptLabel.setText("Please enter subject name");
            newSubject.requestFocus();
            return;
        }

        Subject subject = new Subject(newSubject.getText());
        if(subjects.contains(subject)) {
            promptLabel.setText("Subject already exists");
            newSubject.requestFocus();
            return;
        }

        subjectRepository.addSubject(subject);
        subjects.add(subject);
        promptLabel.setText("Subject added");
        button.setText("Return to Dashboard");

    }

    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/dashboard.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
