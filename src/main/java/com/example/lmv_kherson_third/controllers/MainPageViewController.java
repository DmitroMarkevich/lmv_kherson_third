package com.example.lmv_kherson_third.controllers;

import com.example.lmv_kherson_third.utils.UtilsMethod;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainPageViewController {
    @FXML
    private Button addNewGroupStudentsButton;

    @FXML
    private Button addNewMarksStudentButton;

    @FXML
    private Button addNewStudentButton;

    @FXML
    private Button searchStudentButton;

    @FXML
    void initialize() {
        addNewStudentButton.setOnAction(actionEvent -> {
            addNewStudentButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) addNewStudentButton.getScene().getWindow();
            UtilsMethod.downloaderPage("addNewStudent-page-view.fxml", currentStage);
        });

        searchStudentButton.setOnAction(actionEvent -> {
            searchStudentButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) searchStudentButton.getScene().getWindow();
            UtilsMethod.downloaderPage("searchStudent-page-view.fxml", currentStage);
        });

        addNewGroupStudentsButton.setOnAction(actionEvent -> {
            addNewGroupStudentsButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) addNewGroupStudentsButton.getScene().getWindow();
            UtilsMethod.downloaderPage("addNewStudentGroup-page-view.fxml", currentStage);
        });

        addNewMarksStudentButton.setOnAction(actionEvent -> {
            addNewStudentButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) addNewStudentButton.getScene().getWindow();
            UtilsMethod.downloaderPage("indicationGrades-page-view.fxml", currentStage);
        });
    }
}
