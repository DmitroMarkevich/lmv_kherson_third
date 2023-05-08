package com.example.lmv_kherson_third.controllers;

import com.example.lmv_kherson_third.entities.GroupStudentsDto;
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

public class AddNewStudentController {
    @FXML
    private TextField emailStudentTextField;

    @FXML
    private Button confirmAddNewStudentButton;

    @FXML
    private TextField facultyStudentTextField;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField middleNameStudentTextField;

    @FXML
    private TextField nameStudentTextField;

    @FXML
    private TextField phoneNumberStudentTextField;

    @FXML
    private TextField specialtyStudentTextField;

    @FXML
    private TextField surnameStudentTextField;

    @FXML
    private ComboBox<GroupStudentsDto> groupsComboBox;

    @FXML
    void initialize() {
        ObservableList<GroupStudentsDto> items = FXCollections.observableArrayList(DataManager.getGroupStudentsListDto());
        groupsComboBox.setItems(items);

        goBackButton.setOnAction(actionEvent -> {
            goBackButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
        });

        confirmAddNewStudentButton.setOnAction(actionEvent -> {
            String nameStudent = nameStudentTextField.getText().trim();
            String surnameStudent = surnameStudentTextField.getText().trim();
            String middleNameStudent = middleNameStudentTextField.getText().trim();
            String phoneNumberStudent = phoneNumberStudentTextField.getText().trim();
            String emailStudent = emailStudentTextField.getText().trim();
            String facultyStudent = facultyStudentTextField.getText().trim();
            String specialtyStudent = specialtyStudentTextField.getText().trim();
            GroupStudentsDto selectedStudentGroup = groupsComboBox.getValue();


            if (nameStudent.isEmpty() || surnameStudent.isEmpty() || middleNameStudent.isEmpty() ||
                    phoneNumberStudent.isEmpty() || emailStudent.isEmpty() || facultyStudent.isEmpty() ||
                    specialtyStudent.isEmpty()) {
                UtilsMethod.showErrorAlert("Усі поля повинні бути заповнені!");
                return;
            }

            if (selectedStudentGroup == null) {
                UtilsMethod.showErrorAlert("Виберіть існуючу групу або сторіть її!");
                return;
            }

            try {
                new DataManager().createNewStudent(nameStudent, surnameStudent, middleNameStudent,
                        phoneNumberStudent, emailStudent, facultyStudent, specialtyStudent, selectedStudentGroup.getGroupName(), selectedStudentGroup.getGroupId());
                UtilsMethod.showSuccessAlert("Студент успішно доданий!");
                confirmAddNewStudentButton.getScene().getWindow().hide();
                Stage currentStage = (Stage) confirmAddNewStudentButton.getScene().getWindow();
                UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
            } catch (SQLException e) {
                UtilsMethod.showErrorAlert("Сталася помилка під час підключення до бази даних!");
                throw new RuntimeException(e);
            }
        });
    }
}
