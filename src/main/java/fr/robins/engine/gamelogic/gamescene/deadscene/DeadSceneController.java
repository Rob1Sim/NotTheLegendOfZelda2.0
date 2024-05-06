package fr.robins.engine.gamelogic.gamescene.deadscene;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class DeadSceneController {

    @FXML
    public void onLeave(ActionEvent event){
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
