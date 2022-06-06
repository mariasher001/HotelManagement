package com.marry.hotelmanagement.Controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class LoginAndRegisterController {

    private FadeTransition fadeTransition;
    public AnchorPane LoginAnchorPane;
    public AnchorPane RegisterAnchorPane;

    public void LoginButtonClicked(ActionEvent actionEvent) {
        //Main.setScene("");
    }

    public void RegisterButtonClicked(ActionEvent actionEvent) {
        setVisibility(RegisterAnchorPane,true,false);
        setVisibility(LoginAnchorPane,false,true);
        transition(LoginAnchorPane);
    }

    public void OpenRegisterPage(MouseEvent mouseEvent) {
        setVisibility(LoginAnchorPane,true,false);
        setVisibility(RegisterAnchorPane,false,true);
        transition(RegisterAnchorPane);
    }

    /**
     * Description: sets the visibility of the Anchor-pans
     * @param anchorPane
     * @param disableBool
     * @param visibilityBool
     */
    public void setVisibility(AnchorPane anchorPane, Boolean disableBool, Boolean visibilityBool){
        anchorPane.setDisable(disableBool);
        anchorPane.setVisible(visibilityBool);
    }

    /**
     * Description: Slowly transition from the opacity of 0.0 to 1.0 in the time-interval Of 1 second to the desired Anchor-pan.
     * @param anchorPane
     */
    private void transition(AnchorPane anchorPane) {
        FadeTransition transition = new FadeTransition(Duration.seconds(1.0),anchorPane);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.play();
    }
}
