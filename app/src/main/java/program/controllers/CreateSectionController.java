package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import program.MainApp;
import program.models.Section;
import program.repositories.SectionRepository;

import java.io.IOException;
import java.util.List;

public class CreateSectionController {
    @FXML
    TextField newSection;

    @FXML
    Label promptLabel;

    @FXML
    Button button;

    private List<Section> sections;
    private SectionRepository sectionRepository;

    @FXML
    public void initialize() {
        sectionRepository = new SectionRepository();
        sections = sectionRepository.getAllSections();
    }

    public void addSection() {
        if(newSection.getText().isEmpty()) {
            promptLabel.setText("Please enter section name");
            newSection.requestFocus();
            return;
        }

        Section section = new Section(newSection.getText());
        if(sections.contains(section)) {
            promptLabel.setText("Section already exists");
            newSection.requestFocus();
            return;
        }

        sectionRepository.addSection(section);
        sections.add(section);
        promptLabel.setText("Section added");
        button.setText("Return to Sections");
        newSection.clear();
    }

    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/sections.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Sections");
        stage.show();
    }
}
