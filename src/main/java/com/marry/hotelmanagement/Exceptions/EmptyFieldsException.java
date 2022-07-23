package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class EmptyFieldsException extends Exception {

    public void errorAlertForEmptyField() {
        AllAlerts.errorAlert("EmptyFieldException"
                , "Empty Field Exception Caught!!",
                "Fields cannot be left Empty!!!");
    }
}
