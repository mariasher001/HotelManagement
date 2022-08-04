package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Exceptions.AllAlerts;
import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.*;
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
    private CheckInDataManager checkInDataManager;
    private CustomerDataManager customerDataManager;

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


    public void checkOutDateCheckBoxClicked(ActionEvent actionEvent) throws Exception {
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
                checkOutDateCheckBox.setSelected(false);
            }

        } else {
            checkOutDatePicker.setEditable(true);
        }
    }


    @FXML
    private void confirmCheckInButtonClicked(ActionEvent actionEvent) throws Exception {
        if (customerNameTextField.getText().isEmpty() || typeofIdMenuButton.getText().equals("Choose")
                || idNoTextField.getText().isEmpty() || additionalMembersMenuButton.getText().equals("Choose")
                || checkInDatePicker.getValue() == null || checkOutDatePicker.getValue() == null
                || roomNoChoiceBox.getValue() == null) {
            AllAlerts.errorAlert("EmptyFields", "EmptyFields Found", "Please Check Out Again for Empty Fields and Resubmit the form!!");
            return;
        }
        updateRoomAvailabilityStatus();
        persistInCustomerDataTable();
        persistInCheckInDataTable();

        AllAlerts.confirmAlert("Check-In", "Checked-In!", "Check in was successful");
        Main.setScene("HotelManagementMenu.fxml");
    }

    private void updateRoomAvailabilityStatus() {
        RoomData roomData = roomDataManager.readAllRoomData().stream()
                .filter(roomData1 -> roomData1.getRoomNo().equals(roomNoChoiceBox.getValue().toString())).toList()
                .get(0);
        roomData.setRoomAvailability("BOOKED");
        roomDataManager.updateRoomData(roomData);
    }

    private void persistInCheckInDataTable() {
        checkInDataManager = new CheckInDataManager();
        CheckInData checkInData = new CheckInData();

        checkInData.setName(customerNameTextField.getText());
        checkInData.setTypeOfID(typeofIdMenuButton.getText());
        checkInData.setIdNo(idNoTextField.getText());
        checkInData.setNoOfAdditionalMembers(additionalMembersMenuButton.getText());
        checkInData.setPets("" + petsCheckBox.isSelected());
        if (petsCheckBox.isSelected())
            checkInData.setPetTag(petTagTextField.getText());
        else
            checkInData.setPetTag("NONE");

        checkInData.setCheckInDate(checkInDatePicker.getValue().toString());
        checkInData.setExpectedCheckOut(checkOutDatePicker.getValue().toString());
        checkInData.setEstimatedStay(estimatedStayTextField.getText());
        checkInData.setRoomNo(roomNoChoiceBox.getValue().toString());

        checkInDataManager.setCheckInData(checkInData);
        checkInDataManager.close();
    }

    private void persistInCustomerDataTable() throws Exception {
        customerDataManager = new CustomerDataManager();
        CustomerData customerData = new CustomerData();

        customerData.setName(customerNameTextField.getText());
        customerData.setTypeOfID(typeofIdMenuButton.getText());
        customerData.setIdNo(idNoTextField.getText());
        customerData.setNoOfAdditionalMembers(additionalMembersMenuButton.getText());
        customerData.setPets("" + petsCheckBox.isSelected());
        if (petsCheckBox.isSelected()) {
            customerData.setPetTag(petTagTextField.getText());
        } else {
            customerData.setPetTag("NONE");
        }
        customerData.setCheckInDate(checkInDatePicker.getValue().toString());
        customerData.setCheckOutDate(checkOutDatePicker.getValue().toString());
        customerData.setEstimatedStay(estimatedStayTextField.getText());
        customerData.setRoomNo(roomNoChoiceBox.getValue().toString());
        customerData.setStatus("CHECKED-IN");
        customerData.setBillId("NONE");

        customerDataManager.setCustomerData(customerData);
        customerDataManager.close();
    }


}