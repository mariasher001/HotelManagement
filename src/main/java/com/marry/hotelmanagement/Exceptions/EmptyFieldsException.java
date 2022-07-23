package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class EmptyFieldsException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void errorAlertForEmptyField() {
        AllAlerts.errorAlert("EmptyFieldException"
                , "Empty Field Exception Caught!!",
                "Fields cannot be left Empty!!!");
    }
}
