package com.example.lmv_kherson_third.controllers;

import com.example.lmv_kherson_third.entities.StudentDto;
import com.example.lmv_kherson_third.utils.DataManager;
import com.example.lmv_kherson_third.utils.UtilsMethod;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SearchStudentController {
    @FXML
    public TableColumn<StudentDto, Integer> idStudentColumn;
    @FXML
    private TableView<StudentDto> tableView;
    @FXML
    private TableColumn<StudentDto, String> nameStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> surnameStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> middleNameStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> phoneNumberStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> emailStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> specialtyStudentColumn;
    @FXML
    private TableColumn<StudentDto, String> facultyStudentColumn;
    @FXML
    private TableColumn<StudentDto, Integer> idGroupStudentColumn;

    @FXML
    private TextField nameStudentTextField;

    @FXML
    private TextField surnameStudentTextField;

    @FXML
    private TextField idGroupStudentTextField;

    @FXML
    private Button searchStudentButton;

    @FXML
    private Button deductionStudentButton;

    @FXML
    private Button goBackButton;

    @FXML
    void initialize() {
        nameStudentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameStudentColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        middleNameStudentColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        phoneNumberStudentColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailStudentColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        specialtyStudentColumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        facultyStudentColumn.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        idStudentColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idGroupStudentColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        tableView.setItems(DataManager.getStudentsListDto());

        goBackButton.setOnAction(actionEvent -> {
            goBackButton.getScene().getWindow().hide();
            Stage currentStage = (Stage) goBackButton.getScene().getWindow();
            UtilsMethod.downloaderPage("main-page-view.fxml", currentStage);
        });

        deductionStudentButton.setOnAction(actionEvent -> {
            StudentDto selectedStudent = tableView.getSelectionModel().getSelectedItem();

            if (selectedStudent == null) {
                UtilsMethod.showErrorAlert("Нажміть на студента якого хочете відрахувати!");
                return;
            }

            try {
                new DataManager().deleteStudent(selectedStudent.getStudentId());
                tableView.setItems(DataManager.getStudentsListDto());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            UtilsMethod.showSuccessAlert("Студент відрахований!");
        });

        searchStudentButton.setOnAction(actionEvent -> {
            String studentName = nameStudentTextField.getText().trim();
            String studentSurname = surnameStudentTextField.getText().trim();
            String idGroup = idGroupStudentTextField.getText().trim();

            nameStudentTextField.clear();
            surnameStudentTextField.clear();

            tableView.setItems(new DataManager().searchStudents(studentName, studentSurname, idGroup));
            tableView.refresh();
        });
    }
}
