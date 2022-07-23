package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class DoesNotMatchMinimumPasswordLengthException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForShortPassword(){
        errorAlert.setTitle("MinimumPasswordLengthException");
        errorAlert.setHeaderText("Minimum Password Length Exception caught!!");
        errorAlert.setContentText("Password Length should be greater than 6. Please Try again!");
        errorAlert.showAndWait();
    }
}
