package com.example.lmv_kherson_third;

import com.example.lmv_kherson_third.utils.UtilsMethod;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        UtilsMethod.downloaderPage("main-page-view.fxml", stage);
    }
}