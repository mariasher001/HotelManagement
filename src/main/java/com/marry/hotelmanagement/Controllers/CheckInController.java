package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Exceptions.AllAlerts;
import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.RoomData;
import com.marry.hotelmanagement.Models.RoomDataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CheckInController {
    @FXML
    private CheckBox estimatedStayCheckBox;
    @FXML
    private CheckBox checkOutDateCheckBox;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField idNoTextField;
    @FXML
    private MenuButton additionalMembersMenuButton;
    @FXML
    private TextField petTagTextField;
    @FXML
    private ChoiceBox roomNoChoiceBox;
    @FXML
    private TextField expectedPriceTextField;
    @FXML
    private CheckBox petsCheckBox;
    @FXML
    private DatePicker checkInDatePicker;
    @FXML
    private DatePicker checkOutDatePicker;
    @FXML
    private TextField estimatedStayTextField;
    @FXML
    private Button confirmCheckInButton;
    @FXML
    private MenuButton typeofIdMenuButton;
    @FXML
    private MenuItem passportMenuItem;
    @FXML
    private MenuItem residencePermitMenuItem;
    @FXML
    private MenuItem nicMenuItem;
    @FXML
    private MenuItem oneMenuItem;
    @FXML
    private MenuItem twoMenuItem;
    @FXML
    private MenuItem zeroMenuItem;

    private RoomDataManager roomDataManager;

    @FXML
    private void initialize() {

        roomDataManager = new RoomDataManager();

        List<RoomData> roomDataList = roomDataManager.readAllRoomData()
                .stream().filter(roomData -> roomData.getRoomAvailability().equals("AVAILABLE"))
                .collect(Collectors.toList());

        List<String> roomNumbersOnly = new ArrayList<>();
        for (RoomData roomData : roomDataList) {
            roomNumbersOnly.add(roomData.getRoomNo());
        }

        ObservableList<String> roomNoList = FXCollections.observableArrayList();
        roomNoList.addAll(roomNumbersOnly);
        roomNoChoiceBox.setItems(roomNoList);

        if (!RoomSearchController.SELECTED_ROOM_NUMBER.equals("")) {
            roomNoChoiceBox.setValue(RoomSearchController.SELECTED_ROOM_NUMBER);
        }

        RoomSearchController.SELECTED_ROOM_NUMBER = "";
    }

    @FXML
    private void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("HotelManagementMenu.fxml");
    }

    @FXML
    private void typeOfIdMenuItemClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(passportMenuItem)) {
            typeofIdMenuButton.setText("Passport");
        } else if (actionEvent.getSource().equals(residencePermitMenuItem)) {
            typeofIdMenuButton.setText("Residence Permit");
        } else if (actionEvent.getSource().equals(nicMenuItem)) {
            typeofIdMenuButton.setText("NIC");
        }
    }

    @FXML
    private void additionalMembersMenuItemsClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(zeroMenuItem)) {
            additionalMembersMenuButton.setText("0");
        } else if (actionEvent.getSource().equals(oneMenuItem)) {
            additionalMembersMenuButton.setText("1");
        } else if (actionEvent.getSource().equals(twoMenuItem)) {
            additionalMembersMenuButton.setText("2");

        }
    }


    @FXML
    private void petsCheckBoxClicked(ActionEvent actionEvent) {
        if (petsCheckBox.isSelected()) {
            petTagTextField.setDisable(false);
        } else {
            petTagTextField.setDisable(true);
        }
    }

    @FXML
    private void confirmCheckInButtonClicked(ActionEvent actionEvent) throws IOException {

        AllAlerts.confirmAlert("Check-In", "Checked-In!", "Check in was successful");
        Main.setScene("HotelManagementMenu.fxml");
    }

    public void checkOutDateCheckBoxClicked(ActionEvent actionEvent) {
        if (checkOutDateCheckBox.isSelected()) {
            if (checkInDatePicker.getValue() != null && !estimatedStayTextField.getText().isEmpty()) {
                if (Integer.parseInt(estimatedStayTextField.getText()) < 0) {
                    AllAlerts.errorAlert("Negative Numbers", "Negative Numbers in estimated stay", "Please Use Positive integers only!");
                    checkOutDateCheckBox.setSelected(false);
                    return;
                }
                int NumberOfDays = Integer.parseInt(estimatedStayTextField.getText());
                checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(NumberOfDays));
                checkOutDatePicker.setEditable(false);
            } else {
                AllAlerts.errorAlert("EmptyFields", "Empty Fields Detected", "CheckIn and CheckOutDates fields cannot be left empty!!");
                checkOutDateCheckBox.setSelected(false);
            }

        } else {
            checkOutDatePicker.setEditable(true);
        }
    }

    public void estimatedStayCheckBoxClicked(ActionEvent actionEvent) {
        if (estimatedStayCheckBox.isSelected()) {
            if (checkInDatePicker.getValue() != null && checkOutDatePicker.getValue() != null) {
                if (checkOutDatePicker.getValue().isAfter(checkInDatePicker.getValue())) {
                    long estimatedTime = Duration.between(checkInDatePicker.getValue().atStartOfDay(), checkOutDatePicker.getValue().atStartOfDay()).toDays();
                    estimatedStayTextField.setText("" + estimatedTime);
                    estimatedStayTextField.setEditable(false);
                } else {
                    AllAlerts.errorAlert("WrongCheckOutDate", "Wrong check out date", "Check-out date should not be after check-in date. Please re-enter!!");
                    estimatedStayCheckBox.setSelected(false);
                }
            } else {
                AllAlerts.errorAlert("EmptyFields", "Empty Fields Detected", "CheckIn and CheckOutDates fields cannot be left empty!!");
                estimatedStayCheckBox.setSelected(false);
            }
        } else {
            estimatedStayTextField.setEditable(true);
        }
    }
}