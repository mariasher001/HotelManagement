package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class UserDataAlreadyExistException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForDuplicateDataFound(){
        AllAlerts.errorAlert("UserDataAlreadyExistException",
                "UserDataAlreadyExistException Caught!!",
                "UserData Already Exist!!! Please Try again!" );
    }
}
