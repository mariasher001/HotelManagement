package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class EmptyFieldsException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void errorAlertForEmptyField(){
        errorAlert.setTitle("EmptyFieldException");
        errorAlert.setHeaderText("Empty Field Exception Caught!!");
        errorAlert.setContentText("Fields cannot be left Empty!!!");
        errorAlert.showAndWait();
    }
}
