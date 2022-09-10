package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HotelManagementMenuController {

    public void checkInClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("CheckIn.fxml");
    }

    public void checkOutClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("CheckOut.fxml");
    }

    public void roomSearchClicked(MouseEvent mouseEvent) throws IOException {
        Main.setScene("RoomSearch.fxml");
    }


    public void petManagementClicked(MouseEvent mouseEvent) {
    }

    public void billsClicked(MouseEvent mouseEvent) {
    }
}
