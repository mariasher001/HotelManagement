package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class DoesNotMatchMinimumPasswordLengthException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void errorAlertForShortPassword() {
        AllAlerts.errorAlert("MinimumPasswordLengthException",
                "Minimum Password Length Exception caught!!",
                "Password Length should be greater than 6. Please Try again!");
    }
}
