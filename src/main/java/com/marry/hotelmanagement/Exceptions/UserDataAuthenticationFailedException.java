package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class UserDataAuthenticationFailedException extends Exception{
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public void errorAlertForUserDataMismatch(){
        errorAlert.setTitle("UserDataAuthenticationFailedException");
        errorAlert.setHeaderText("UserDataAuthentication Exception Caught!!");
        errorAlert.setContentText("Login Username Or Password Don't match! Try Again");
        errorAlert.showAndWait();

    }
}
