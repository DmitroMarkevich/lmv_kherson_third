package com.example.lmv_kherson_third.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static DatabaseConnection connect;

    static {
        try {
            connect = new DatabaseConnection();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DatabaseConnection() throws SQLException, IOException {
        connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
    }

    public static Connection getInstance() {
        if (connect == null) {
            try {
                connect = new DatabaseConnection();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    public String getUrl() {
        return "jdbc:mysql://localhost:3306/vntu";
    }

    public String getUsername() {
        return "root";
    }

    public String getPassword() {
        return "test123q";
    }
}
