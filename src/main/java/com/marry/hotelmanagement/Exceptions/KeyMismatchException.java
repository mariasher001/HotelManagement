package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class KeyMismatchException extends Exception {

    public void errorAlertForKeyMismatch() {
        AllAlerts.errorAlert("KeyMismatchException"
                , "KeyMismatchException caught!!",
                "Keys do not match. Please Try again :(");
    }
}
