package com.marry.hotelmanagement.Controllers;

import com.marry.hotelmanagement.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class PrimaryController {


    public void startButtonClicked(ActionEvent actionEvent) throws IOException {
        Main.setScene("LoginAndRegister.fxml");
    }
}
