package com.marry.hotelmanagement.Exceptions;

import javafx.scene.control.Alert;

public class UserDataAuthenticationFailedException extends Exception {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    public void errorAlertForUserDataMismatch() {
        AllAlerts.errorAlert("UserDataAuthenticationFailedException",
                "UserDataAuthentication Exception Caught!!",
                "Login Username Or Password Don't match! Try Again");
    }
}
