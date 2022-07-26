package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.RoomData;
import com.marry.hotelmanagement.Models.RoomDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RoomSearchController {
    public TableView roomSearchTableView;
    public TableColumn roomNo;
    public TableColumn typeofRoom;
    public TableColumn noOfBeds;
    public TableColumn price;
    public TableColumn roomAvailability;
    public MenuButton typeOfRoomMenuButton;
    public MenuItem ACMenuItem;
    public MenuItem NonACMenuItem;
    public MenuButton NumberOfBedsMenuButton;
    public MenuItem OneBedMenuItem;
    public MenuItem TwoBedMenuItem;
    public MenuItem ThreeBedMenuItem;
    public MenuButton OrderByMenuButton;
    public MenuItem NoOfBedsMenuItem;
    public MenuItem PriceMenuItem;
    public Button bookButton;

    private RoomDataManager roomDataManager;
    private List<RoomData> roomDataList;
    private List<RoomData> roomDataList1 = null;

    @FXML
    public void initialize() {
        roomDataManager = new RoomDataManager();
        if (roomDataManager.readAllRoomData().isEmpty()) {
            initializeRoomData();
        }
        linkRoomSearchTableViewColumns();
        roomSearchTableView.getItems().addAll(roomDataManager.readAllRoomData());

    }

    private void initializeRoomData() {
        for (int i = 1; i <= 60; i++) {
            RoomData tableRow = null;
            if (i < 10) {
                tableRow = new RoomData("" + i, "NON-AC", "1", "10000", "AVAILABLE");
            } else if (i <= 20) {
                tableRow = new RoomData("" + i, "NON-AC", "2", "15000", "AVAILABLE");
            } else if (i <= 30) {
                tableRow = new RoomData("" + i, "NON-AC", "3", "20000", "AVAILABLE");
            } else if (i <= 40) {
                tableRow = new RoomData("" + i, "AC", "1", "20000", "AVAILABLE");
            } else if (i <= 50) {
                tableRow = new RoomData("" + i, "AC", "2", "25000", "AVAILABLE");
            } else if (i <= 60) {
                tableRow = new RoomData("" + i, "AC", "3", "30000", "AVAILABLE");
            }
            roomDataManager.setRoomData(tableRow);
        }

    }

    private void linkRoomSearchTableViewColumns() {

        roomNo.setCellValueFactory(new PropertyValueFactory<RoomData, String>("roomNo"));
        typeofRoom.setCellValueFactory(new PropertyValueFactory<RoomData, String>("typeOfRoom"));
        noOfBeds.setCellValueFactory(new PropertyValueFactory<RoomData, String>("noOfBeds"));
        price.setCellValueFactory(new PropertyValueFactory<RoomData, String>("price"));
        roomAvailability.setCellValueFactory(new PropertyValueFactory<RoomData, String>("roomAvailability"));


    }

    public void ACMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().filter(roomData -> roomData.getTypeOfRoom().equals("AC"))
                .collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void NonACMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().filter(roomData -> roomData.getTypeOfRoom().equals("NON-AC"))
                .collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void OneBedMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().filter(roomData -> roomData.getNoOfBeds().equals("1"))
                .collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void TwoBedMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().filter(roomData -> roomData.getNoOfBeds().equals("2"))
                .collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void ThreeBedMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().filter(roomData -> roomData.getNoOfBeds().equals("3"))
                .collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void NoOfBedsMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().sorted((o1, o2) -> o1.getNoOfBeds().compareTo(o2.getNoOfBeds())).collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void PriceMenuItemClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomDataList = roomDataList.stream().sorted((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())).collect(Collectors.toList());
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void ClearFiltersButtonClicked(ActionEvent actionEvent) {
        roomSearchTableView.getItems().clear();
        roomDataList = roomDataManager.readAllRoomData();
        roomSearchTableView.getItems().addAll(roomDataList);
    }

    public void BookButtonClicked(ActionEvent actionEvent) {
        for (int i = 0; i < 60; i++) {
            if (roomSearchTableView.getSelectionModel().isSelected(i)) {
                RoomData roomData = (RoomData) roomSearchTableView.getSelectionModel().getSelectedItem();
                if (roomData.getRoomAvailability().equals("AVAILABLE")) {
                    bookRoom(actionEvent, roomData);
                } else {
                    unbookRoom(actionEvent, roomData);
                }
                break;
            }
        }
    }

    private void unbookRoom(ActionEvent actionEvent, RoomData roomData) {
        roomData.setRoomAvailability("AVAILABLE");
        roomDataManager.updateRoomData(roomData);
        ClearFiltersButtonClicked(actionEvent);
    }

    private void bookRoom(ActionEvent actionEvent, RoomData roomData) {
        roomData.setRoomAvailability("BOOKED");
        roomDataManager.updateRoomData(roomData);
        ClearFiltersButtonClicked(actionEvent);
        //toDo: Send room-details to check-in.
    }


    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("HotelManagementMenu.fxml");
    }

    public void roomSearchTableViewClicked(MouseEvent mouseEvent) {
        for (int i = 0; i < 60; i++) {
            if (roomSearchTableView.getSelectionModel().isSelected(i)) {
                RoomData roomData = (RoomData) roomSearchTableView.getSelectionModel().getSelectedItem();
                if (roomData.getRoomAvailability().equals("AVAILABLE")) {
                    bookButton.setDisable(false);
                } else {
                    bookButton.setDisable(true);
                }
                break;
            }
        }
    }
}
