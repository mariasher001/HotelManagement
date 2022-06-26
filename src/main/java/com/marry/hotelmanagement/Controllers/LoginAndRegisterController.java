package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Exceptions.*;
import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.UserData;
import com.marry.hotelmanagement.Models.UserDataManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class LoginAndRegisterController {

    public TextField loginUserNameTextField;
    public PasswordField loginPasswordPasswordField;
    public TextField registerUsernameTextField;
    public TextField registerEmailTextField;
    public PasswordField registerPasswordTextField;
    public TextField registerKeyTextField;
    public AnchorPane LoginAnchorPane;
    public AnchorPane RegisterAnchorPane;
    private UserDataManager userDataManager;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private Alert confirmationAlert = new Alert((Alert.AlertType.CONFIRMATION));

    public void LoginButtonClicked(ActionEvent actionEvent) {
        try {
            UserDataAuthentication();
            Main.setScene("HotelManagementMenu.fxml");
        } catch (UserDataAuthenticationFailedException e) {
            errorAlert.setTitle("UserDataAuthenticationFailedException");
            errorAlert.setHeaderText("UserDataAuthentication Exception Caught!!");
            errorAlert.setContentText("Login Username Or Password Don't match! Try Again");
            errorAlert.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void UserDataAuthentication() throws UserDataAuthenticationFailedException {
        userDataManager = new UserDataManager();
        List<UserData> userDataList = userDataManager.readAllUserData();
        for (UserData userData : userDataList) {
            if (loginUserNameTextField.getText().equals(userData.getUsername()) && loginPasswordPasswordField.getText().equals(userData.getPassword())) {
                return;
            }
        }
        throw new UserDataAuthenticationFailedException();
    }

    public void RegisterButtonClicked(ActionEvent actionEvent) {
        try {
            validateUserRegistration();
            userDataManager = new UserDataManager();
            UserData userData = new UserData();
            userData.setUsername(registerUsernameTextField.getText());
            userData.setEmail(registerEmailTextField.getText());
            userData.setPassword(registerPasswordTextField.getText());
            userDataManager.setUserData(userData);
            userDataManager.close();

            confirmationAlert.setTitle("Registration");
            confirmationAlert.setHeaderText("Registration Successful");
            confirmationAlert.setContentText("The User is registered Successfully");
            confirmationAlert.showAndWait();

            resetFields();
            setVisibility(RegisterAnchorPane, true, false);
            setVisibility(LoginAnchorPane, false, true);
            transition(LoginAnchorPane);

        } catch (EmptyFieldsException e) {
            errorAlert.setTitle("EmptyFieldException");
            errorAlert.setHeaderText("Empty Field Exception Caught!!");
            errorAlert.setContentText("Fields cannot be left Empty!!!");
            errorAlert.showAndWait();
        } catch (UserDataAlreadyExistException e) {
            errorAlert.setTitle("UserDataAlreadyExistException");
            errorAlert.setHeaderText("UserDataAlreadyExistException Caught!!");
            errorAlert.setContentText("UserData Already Exist!!! Please Try again!");
            errorAlert.showAndWait();
        } catch (DoesNotMatchMinimumPasswordLengthException e) {
            errorAlert.setTitle("MinimumPasswordLengthException");
            errorAlert.setHeaderText("Minimum Password Length Exception caught!!");
            errorAlert.setContentText("Password Length should be greater than 6. Please Try again!");
            errorAlert.showAndWait();
        } catch (KeyMismatchException e) {
            errorAlert.setTitle("KeyMismatchException");
            errorAlert.setHeaderText("KeyMismatchException caught!!");
            errorAlert.setContentText("Keys do not match. Please Try again :(");
            errorAlert.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void validateUserRegistration() throws EmptyFieldsException, UserDataAlreadyExistException, DoesNotMatchMinimumPasswordLengthException, KeyMismatchException {
        if (registerUsernameTextField.getText().isEmpty() || registerEmailTextField.getText().isEmpty() || registerPasswordTextField.getText().isEmpty()
                || registerKeyTextField.getText().isEmpty()) {
            throw new EmptyFieldsException();
        }

        userDataManager = new UserDataManager();
        List<UserData> userDataList = userDataManager.readAllUserData();
        for (UserData userdata : userDataList) {
            if (userdata.getUsername().equals(registerUsernameTextField.getText())
                    || userdata.getEmail().equals(registerEmailTextField.getText())) {
                throw new UserDataAlreadyExistException();
            }
        }
        try {
            userDataManager.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (registerPasswordTextField.getText().length() < 6) {
            throw new DoesNotMatchMinimumPasswordLengthException();
        }

        if (!registerKeyTextField.getText().equals("KeyLOL")) {
            throw new KeyMismatchException();
        }


    }

    public void resetFields() {
        loginUserNameTextField.setText("");
        loginPasswordPasswordField.setText("");
        registerUsernameTextField.setText("");
        registerPasswordTextField.setText("");
        registerEmailTextField.setText("");
        registerKeyTextField.setText("");

    }

    public void OpenRegisterPage(MouseEvent mouseEvent) {
        setVisibility(LoginAnchorPane, true, false);
        setVisibility(RegisterAnchorPane, false, true);
        transition(RegisterAnchorPane);
    }

    /**
     * Description: sets the visibility of the Anchor-pans
     *
     * @param anchorPane
     * @param disableBool
     * @param visibilityBool
     */
    public void setVisibility(AnchorPane anchorPane, Boolean disableBool, Boolean visibilityBool) {
        anchorPane.setDisable(disableBool);
        anchorPane.setVisible(visibilityBool);
    }

    /**
     * Description: Slowly transition from the opacity of 0.0 to 1.0 in the time-interval Of 1 second to the desired Anchor-pan.
     *
     * @param anchorPane
     */
    private void transition(AnchorPane anchorPane) {
        FadeTransition transition = new FadeTransition(Duration.seconds(1.0), anchorPane);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.play();
    }

    public void backButtonClicked(MouseEvent mouseEvent) {
        resetFields();
        setVisibility(RegisterAnchorPane, true, false);
        setVisibility(LoginAnchorPane, false, true);
        transition(LoginAnchorPane);
    }
}
