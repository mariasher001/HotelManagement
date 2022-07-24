package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RoomSearchController {
    public TableView roomSearchTableView;
    public TableColumn roomNo;
    public TableColumn typeofRoom;
    public TableColumn noOfBeds;
    public TableColumn price;
    public TableColumn RoomAvailability;
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
