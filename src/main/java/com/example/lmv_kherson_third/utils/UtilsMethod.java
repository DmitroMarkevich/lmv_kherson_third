package com.example.lmv_kherson_third.utils;

import com.example.lmv_kherson_third.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UtilsMethod {
    public static void downloaderPage(String url, Stage currentStage) {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(url));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("OXFORD");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initOwner(currentStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        currentStage.close();
    }

    public static void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успішно!");
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
