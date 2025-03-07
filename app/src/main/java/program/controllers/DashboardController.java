package program.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.*;

import program.MainApp;
import program.models.Section;
import program.repositories.SectionRepository;

import java.io.IOException;
import java.util.*;

public class DashboardController {
    @FXML
    private TextField searchSectionField;

    @FXML
    private ListView<String> sectionListView;

    @FXML
    private Label deleteErrorLabel;

    private SectionRepository sectionRepository;
    private ObservableList<String> sections;

    @FXML
    public void initialize() {
        sectionRepository = new SectionRepository();

        sections = FXCollections.observableArrayList();

        sectionListView.setItems(sections);

        List<Section> allsections = sectionRepository.getAllSections();
        updateSectionListView(allsections);
    }

    public void updateSectionListView(List<Section> newSections) {
        sections.clear();

        for (Section section : newSections) {
            this.sections.add(section.getName());
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

    public void deleteSection() throws IOException {
        if(sections.isEmpty()) {
            deleteErrorLabel.setText("There are no sections to delete");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("program/deleteSection.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.setTitle("Delete Section");
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