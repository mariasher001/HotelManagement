package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class KeyMismatchException extends Exception {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForKeyMismatch(){
        errorAlert.setTitle("KeyMismatchException");
        errorAlert.setHeaderText("KeyMismatchException caught!!");
        errorAlert.setContentText("Keys do not match. Please Try again :(");
        errorAlert.showAndWait();
    }
}
