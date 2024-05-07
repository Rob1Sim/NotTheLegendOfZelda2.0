package fr.robins.engine.gamelogic.gamescene.deadscene;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;

public class DeadGameScene extends GameScene {
    public DeadGameScene() {
        super();


        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/deadScene.fxml");

            pane = loader.load(fxmlStream);

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  " + e);
        }
    }
}
