package com.example.lmv_kherson_third.controllers;

import com.example.lmv_kherson_third.entities.StudentCharacteristics;
import com.example.lmv_kherson_third.entities.StudentDto;
import com.example.lmv_kherson_third.utils.DataManager;
import com.example.lmv_kherson_third.utils.UtilsMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;

public class ChangeDataStudentController {
    @FXML
    private Button confirmChangeDataStudentButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ComboBox<StudentDto> listStudentsComboBox;

    @FXML
    private ComboBox<StudentCharacteristics> studentCharacteristicsComboBox;

    @FXML
    private TextField newStudentDataTextField;

    @FXML
    void initialize() throws SQLException {
        ObservableList<StudentDto> students = FXCollections.observableArrayList(DataManager.getStudentsListDto());
        listStudentsComboBox.setItems(students);

        studentCharacteristicsComboBox.getItems().setAll(Arrays.asList(StudentCharacteristics.values()));
        studentCharacteristicsComboBox.setValue(StudentCharacteristics.NAME);

        goBackButton.setOnAction(actionEvent -> {
            goBackButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
        });

        confirmChangeDataStudentButton.setOnAction(actionEvent -> {
            if (listStudentsComboBox.getValue() == null) {
                UtilsMethod.showErrorAlert("Виберіть студента!");
                return;
            }

            if (newStudentDataTextField.getText().trim().isEmpty()) {
                UtilsMethod.showErrorAlert("Введіть нове значення!");
                return;
            }

            int studentId = listStudentsComboBox.getValue().getStudentId();
            String newStudentData = newStudentDataTextField.getText().trim();

            try {
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.NAME)) {
                    new DataManager().updateStudentField(studentId, "name", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }

                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.SURNAME)) {
                    new DataManager().updateStudentField(studentId, "surname", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.MIDDLENAME)) {
                    new DataManager().updateStudentField(studentId, "middle_name", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.EMAIL)) {
                    new DataManager().updateStudentField(studentId, "email", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.PHONENUMBER)) {
                    new DataManager().updateStudentField(studentId, "phone_number", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.FACULTY)) {
                    new DataManager().updateStudentField(studentId, "faculty", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.SPECIALTY)) {
                    new DataManager().updateStudentField(studentId, "specialty", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                if (studentCharacteristicsComboBox.getValue().equals(StudentCharacteristics.GROUP)) {
                    new DataManager().updateStudentField(studentId, "nameGroup", newStudentData);
                    UtilsMethod.showSuccessAlert("Дані студента успішно змінені!");
                }
                confirmChangeDataStudentButton.getScene().getWindow().hide();
                Stage currentStage = (Stage) confirmChangeDataStudentButton.getScene().getWindow();
                UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
