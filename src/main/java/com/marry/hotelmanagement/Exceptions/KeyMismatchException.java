package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class KeyMismatchException extends Exception {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForKeyMismatch(){
        AllAlerts.errorAlert("KeyMismatchException"
                ,"KeyMismatchException caught!!",
                "Keys do not match. Please Try again :(");
    }
}
