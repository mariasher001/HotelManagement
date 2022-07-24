package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import com.marry.hotelmanagement.Models.RoomData;
import com.marry.hotelmanagement.Models.RoomDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

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

    private RoomDataManager roomDataManager;

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
            roomSearchTableView.getItems().add(tableRow);
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
    }

    public void NonACMenuItemClicked(ActionEvent actionEvent) {
    }

    public void OneBedMenuItemClicked(ActionEvent actionEvent) {
    }

    public void TwoBedMenuItemClicked(ActionEvent actionEvent) {
    }

    public void ThreeBedMenuItemClicked(ActionEvent actionEvent) {
    }

    public void NoOfBedsMenuItemClicked(ActionEvent actionEvent) {
    }

    public void PriceMenuItemClicked(ActionEvent actionEvent) {
    }

    public void ClearFiltersButtonClicked(ActionEvent actionEvent) {
    }

    public void BookButtonClicked(ActionEvent actionEvent) {
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("HotelManagementMenu.fxml");
    }
}
