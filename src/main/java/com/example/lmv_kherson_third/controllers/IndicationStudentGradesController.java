package com.example.lmv_kherson_third.controllers;

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
import java.util.List;

public class IndicationStudentGradesController {
    @FXML
    private Button confirmAddNewStudentMarkButton;

    @FXML
    private Button goBackButton;

    @FXML
    private ComboBox<StudentDto> listStudentsComboBox;

    @FXML
    private ComboBox<String> listSubjectsComboBox;

    @FXML
    private TextField studentSubjectMarkTextField;

    @FXML
    void initialize() {
        ObservableList<StudentDto> students = FXCollections.observableArrayList(DataManager.getStudentsListDto());
        listStudentsComboBox.setItems(students);

        ObservableList<String> subjects = FXCollections.observableArrayList(List.of("Вища математика",
                "Дискретна математика", "Математичний аналіз", "Комп'ютерна графіка")
        );
        listSubjectsComboBox.setItems(subjects);
        listSubjectsComboBox.setValue("Вища математика");

        goBackButton.setOnAction(actionEvent -> {
            goBackButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
        });

        confirmAddNewStudentMarkButton.setOnAction(actionEvent -> {
            try {
                if (listStudentsComboBox.getValue() == null) {
                    UtilsMethod.showErrorAlert("Виберіть студента!");
                    return;
                }

                if (studentSubjectMarkTextField.getText().trim().isEmpty()) {
                    UtilsMethod.showErrorAlert("Введіть оцінку!");
                    return;
                }

                int studentId = listStudentsComboBox.getValue().getStudentId();
                String selectedSubject = listSubjectsComboBox.getValue();
                double studentMark = Double.parseDouble(studentSubjectMarkTextField.getText().trim());

                if (studentMark <= 0 || studentMark > 100) {
                    UtilsMethod.showErrorAlert("Оцінка студента не може бути менше 0 або більше 100!");
                    return;
                }

                new DataManager().addNewStudentMark(studentId, selectedSubject, studentMark);

                confirmAddNewStudentMarkButton.getScene().getWindow().hide();
                Stage currentStage = (Stage) confirmAddNewStudentMarkButton.getScene().getWindow();

                UtilsMethod.showSuccessAlert("Ви успішно вказали оцінку студента!");
                UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
            } catch (NumberFormatException exc) {
                UtilsMethod.showErrorAlert("Перевірте введені значення!");
                exc.printStackTrace();
            } catch (SQLException e) {
                UtilsMethod.showErrorAlert("Сталася помилка під час підключення до бази даних!");
                e.printStackTrace();
            }
        });
    }
}
