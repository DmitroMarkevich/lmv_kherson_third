module com.example.lmv_kherson_third {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.lmv_kherson_third to javafx.fxml;
    exports com.example.lmv_kherson_third;
    exports com.example.lmv_kherson_third.controllers;
    opens com.example.lmv_kherson_third.controllers to javafx.fxml;
    exports com.example.lmv_kherson_third.entities;
    opens com.example.lmv_kherson_third.entities to javafx.fxml;
    exports com.example.lmv_kherson_third.utils;
    opens com.example.lmv_kherson_third.utils to javafx.fxml;
}