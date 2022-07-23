package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class UserDataAlreadyExistException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForDuplicateDataFound(){
        errorAlert.setTitle("UserDataAlreadyExistException");
        errorAlert.setHeaderText("UserDataAlreadyExistException Caught!!");
        errorAlert.setContentText("UserData Already Exist!!! Please Try again!");
        errorAlert.showAndWait();
    }
}
