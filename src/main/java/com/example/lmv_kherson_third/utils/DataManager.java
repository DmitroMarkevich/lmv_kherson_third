package com.example.lmv_kherson_third.utils;

import com.example.lmv_kherson_third.utils.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentManager {
    public void createNewStudent(String name, String surname, String middleName,
                              String email, String phoneNumber,
                              String faculty, String specialty,
                              String groupId) throws SQLException {
        String query = "INSERT INTO students (name, surname, middleName, email, phone, faculty, specialty, group_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = DatabaseConnection.getInstance().prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, surname);
        pst.setString(3, middleName);
        pst.setString(4, email);
        pst.setString(5, phoneNumber);
        pst.setString(6, faculty);
        pst.setString(7, specialty);
        pst.setInt(8, Integer.parseInt(groupId));
        pst.execute();
        DatabaseConnection.getInstance().close();
    }
}
