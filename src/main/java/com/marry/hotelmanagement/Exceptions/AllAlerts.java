package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class AllAlerts {
    private static Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    private static Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public static void confirmAlert(String title, String header, String content) {
        confirmationAlert.setTitle(title);
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        confirmationAlert.showAndWait();
    }

    public static void errorAlert(String title, String header, String content) {
        errorAlert.setTitle(title);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}
