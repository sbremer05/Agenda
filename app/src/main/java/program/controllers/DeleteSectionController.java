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
import program.repositories.SectionRepository;

import java.io.IOException;
import java.util.List;

public class DeleteSectionController {
    @FXML
    private ListView<String> sectionListView;

    @FXML
    private Button deleteButton;

    @FXML
    private Button returnButton;

    @FXML
    private Label promptLabel;

    private Section section;
    private SectionRepository sectionRepository;
    private ObservableList<String> sections;

    @FXML
    public void initialize() {
        sectionRepository = new SectionRepository();

        sections = FXCollections.observableArrayList();
        sectionListView.setItems(sections);

        sectionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deleteButton.setDisable(false);
                section = sectionRepository.findByName(newValue);
            } else {
                deleteButton.setDisable(true);
                section = null;
            }
        });

        List<Section> allsections = sectionRepository.getAllSections();
        updateSectionListView(allsections);
    }


    public void updateSectionListView(List<Section> newSections) {
        sections.clear();

        for(Section section : newSections) {
            this.sections.add(section.getName());
        }
    }

    public void sectionSelected(javafx.scene.input.MouseEvent event) {
        section = sectionRepository.findByName(sectionListView.getSelectionModel().getSelectedItem());
    }

    public void deleteSection() {
        // Ask for confirmation before deletion
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Section");
        confirmationAlert.setHeaderText("Are you sure you want to delete this section?");
        confirmationAlert.setContentText("This action cannot be undone.");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                sectionRepository.deleteSection(section);

                sectionListView.getSelectionModel().clearSelection();
                updateSectionListView(sectionRepository.getAllSections());

                returnButton.setText("Return to Dashboard");
            }
        });
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
