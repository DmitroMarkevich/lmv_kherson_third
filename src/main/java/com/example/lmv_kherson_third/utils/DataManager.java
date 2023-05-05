package com.example.lmv_kherson_third.utils;

import com.example.lmv_kherson_third.entities.GroupStudentsDto;
import com.example.lmv_kherson_third.entities.StudentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {
    private static ObservableList<GroupStudentsDto> groupStudentsListDto;
    private static ObservableList<StudentDto> studentsListDto;

    static {
        try {
            groupStudentsListDto = getAllGroupsFromDataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            studentsListDto = getListStudentsFromDataBase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<GroupStudentsDto> getGroupStudentsListDto() {
        return groupStudentsListDto;
    }

    private static ObservableList<StudentDto> getListStudentsFromDataBase() throws SQLException {
        ObservableList<StudentDto> studentDtoList = FXCollections.observableArrayList();
        String sqlCommand = "SELECT * FROM students";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(sqlCommand);
        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            int studentId = resultSet.getInt("id");
            String studentName = resultSet.getString("name");
            String studentSurname = resultSet.getString("surname");
            String studentMiddleName = resultSet.getString("middle_name");
            String studentEmail = resultSet.getString("email");
            String studentPhoneNumber = resultSet.getString("phone_number");
            String studentFaculty = resultSet.getString("faculty");
            String studentSpecialty = resultSet.getString("specialty");
            int studentGroup = resultSet.getInt("group_id");

            StudentDto studentDto = new StudentDto(studentId, studentName, studentSurname,
                    studentMiddleName, studentPhoneNumber, studentEmail,
                    studentFaculty, studentSpecialty, studentGroup);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    public static ObservableList<StudentDto> getStudentsListDto() {
        return studentsListDto;
    }

    public static ObservableList<GroupStudentsDto> getAllGroupsFromDataBase() throws SQLException {
        ObservableList<GroupStudentsDto> allGroupsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM student_group";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(query);
        ResultSet resultSet = pst.executeQuery();

        while (resultSet.next()) {
            int groupId = resultSet.getInt("id");
            String groupName = resultSet.getString("group_name");
            GroupStudentsDto groupStudentDto = new GroupStudentsDto(groupId, groupName);
            allGroupsList.addAll(groupStudentDto);
        }
        return allGroupsList;
    }

    public void createNewStudent(String name, String surname, String middleName,
                                 String email, String phoneNumber,
                                 String faculty, String specialty,
                                 int groupId) throws SQLException {
        String query = "INSERT INTO students (name, surname, middle_name, email, phone_number, group_id, faculty, specialty) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, middleName);
        pst.setString(4, email);
        pst.setString(5, phoneNumber);
        pst.setInt(6, groupId);
        pst.setString(7, faculty);
        pst.setString(8, specialty);
        pst.execute();
        studentsListDto = getListStudentsFromDataBase();
    }

    public void createNewStudentGroup(String name) throws SQLException {
        String query = "INSERT INTO student_group (group_name) VALUES (?)";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(query);
        pst.setString(1, name);
        pst.execute();
        groupStudentsListDto = getAllGroupsFromDataBase();
    }

    public void deleteStudent(int studentId) throws SQLException {
        String studentDeletionQuery = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement deleteStudentStatement = DatabaseConnection.getInstance().prepareStatement(studentDeletionQuery)) {
            deleteStudentStatement.setInt(1, studentId);
            deleteStudentStatement.executeUpdate();
        }

        String gradeDeletionQuery = "DELETE FROM grades WHERE student_id = ?";
        try (PreparedStatement deleteGradesStatement = DatabaseConnection.getInstance().prepareStatement(gradeDeletionQuery)) {
            deleteGradesStatement.setInt(1, studentId);
            deleteGradesStatement.executeUpdate();
        }

        studentsListDto = getListStudentsFromDataBase();
    }

    public void addNewStudentMark(int studentId, String subject, double grade) throws SQLException {
        String query = "INSERT INTO grades (student_id, subject, grade) VALUES (?, ?, ?)";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(query);
        pst.setInt(1, studentId);
        pst.setString(2, subject);
        pst.setDouble(3, grade);
        pst.executeUpdate();
    }

    public FilteredList<StudentDto> searchStudents(String name, String surname, String idGroup) {
        FilteredList<StudentDto> filteredList = new FilteredList<>(getStudentsListDto(), p -> true);

        if (!name.isEmpty()) {
            filteredList = filteredList.filtered(student -> student.getName().equalsIgnoreCase(name));
        }

        if (!surname.isEmpty()) {
            filteredList = filteredList.filtered(student -> student.getSurname().equalsIgnoreCase(surname));
        }

        if (!idGroup.isEmpty()) {
            filteredList = filteredList.filtered(student -> String.valueOf(student.getGroupId()).equals(idGroup));
        }

        return filteredList;
    }
}
