package com.example.lmv_kherson_third.controllers;

import com.example.lmv_kherson_third.utils.DataManager;
import com.example.lmv_kherson_third.utils.UtilsMethod;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddNewStudentGroupViewController {
    @FXML
    private Button confirmAddNewStudentGroupButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField nameGroupTextField;

    @FXML
    void initialize() {
        goBackButton.setOnAction(actionEvent -> {
            goBackButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
        });

        confirmAddNewStudentGroupButton.setOnAction(actionEvent -> {
            String groupStudentName = nameGroupTextField.getText().trim();
            try {
                new DataManager().createNewStudentGroup(groupStudentName);
                confirmAddNewStudentGroupButton.getScene().getWindow().hide();
                Stage currentStage = (Stage) confirmAddNewStudentGroupButton.getScene().getWindow();
                UtilsMethod.showSuccessAlert("Група успішно створена!");
                UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
            } catch (SQLException e) {
                UtilsMethod.showErrorAlert("Сталася помилка під час підключення до бази даних!");
                throw new RuntimeException(e);
            }
        });
    }
}
