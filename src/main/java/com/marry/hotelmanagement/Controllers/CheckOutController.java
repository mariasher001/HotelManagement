package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CheckOutController {

    public TableColumn roomNumber;
    public TableColumn additionalMembers;
    public TableColumn checkInDate;
    public TableColumn petTag;
    public TableColumn idNo;
    public TableColumn name;
    public TableView checkOutTableView;

    public TextField IdFilterTextField;
    public TextField nameFilterTextField;
    public TextField roomNoFilterTextField;

    private CheckInDataManager checkInDataManager;
    private CustomerDataManager customerDataManager;
    private BillDataManger billDataManger;
    private RoomDataManager roomDataManager;

    @FXML
    public void initialize(){
        linkCheckInDataToCheckOutTableViewColumns();

        //create a manager,read all data from checkin db and add it to the list.
        checkInDataManager = new CheckInDataManager();
        List<CheckInData> checkInDataList = checkInDataManager.readAllCheckInData();
        checkOutTableView.getItems().addAll(checkInDataList);
    }

    private void linkCheckInDataToCheckOutTableViewColumns() {
        name.setCellValueFactory(new PropertyValueFactory<CheckInData,String>("name"));
        idNo.setCellValueFactory(new PropertyValueFactory<CheckInData,String >("idNo"));
        additionalMembers.setCellValueFactory(new PropertyValueFactory<CheckInData,String >("noOfAdditionalMembers"));
        petTag.setCellValueFactory(new PropertyValueFactory<CheckInData,String>("petTag"));
        checkInDate.setCellValueFactory(new PropertyValueFactory<CheckInData,String>("checkInDate"));
        roomNumber.setCellValueFactory(new PropertyValueFactory<CheckInData,String>("roomNo"));
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("HotelManagementMenu.fxml");
    }

    public void searchButtonClicked(ActionEvent actionEvent) {

    }

    public void checkOutButtonClicked(ActionEvent actionEvent) {
        for (int i = 0; i < checkInDataManager.readAllCheckInData().size(); i++) {
            if(checkOutTableView.getSelectionModel().isSelected(i)){
                CheckInData checkInData = (CheckInData) checkOutTableView.getSelectionModel().getSelectedItem();
                checkOut(checkInData);
                break;
            }
        }

    }

    private void checkOut(CheckInData checkInData) {
        // delete checkin data
        //customer data update check out status, checkout date, amount recalculate, bill id.
        // bill database create

       customerDataManager = new CustomerDataManager();
       billDataManger = new BillDataManger();

       CustomerData customerData = customerDataManager.readAllCustomerData().stream()
                .filter(customerData1 -> customerData1.getIdNo().equals(checkInData.getIdNo()))
                .toList().get(0);


        BillData billData = new BillData();
        billData.setCustomerDataId(customerData.getId());
        int amount = calculateAmount(customerData);
        billData.setAmount(amount);
        billData.setBillId(UUID.randomUUID().toString());
        billData.setBillStatus("UNPAID");

        customerData.setCheckOutDate(LocalDate.now().toString());
        customerData.setStatus("CHECKED-OUT");
        customerData.setBillId(billData.getBillId());


        billDataManger.setBillData(billData);
        customerDataManager.updateCustomerData(customerData);
        checkInDataManager.deleteCheckInData(checkInData);
        //TODO
        //jump to bills with bill id

    }

    private int calculateAmount(CustomerData customerData) {
        int amount = 0;
        roomDataManager = new RoomDataManager();
        RoomData roomData = roomDataManager.readAllRoomData().stream()
                .filter(roomData1 -> roomData1.getRoomNo().equals(customerData.getRoomNo())).toList().get(0);

        long noOfDaysOfStay = Duration.between(LocalDate.parse(customerData.getCheckInDate()).atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
        amount = (int) (Integer.parseInt(roomData.getPrice())*noOfDaysOfStay);
        if (customerData.getPets().equals(true))
            amount+=2000;
        return amount;
    }


}
