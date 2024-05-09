package fr.robins.engine.gamelogic.gamescene.endscene;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;

public class WinGameScene extends GameScene {
    public WinGameScene() {
        super();


        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/winScene.fxml");

            pane = loader.load(fxmlStream);

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  " + e);
        }
    }
}
