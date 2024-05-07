package fr.robins.engine.gamelogic.gamescene.inventoryscene;

import fr.robins.engine.gamelogic.gamescene.GameScene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class InventoryGameScene extends GameScene {
    public InventoryGameScene() {
        super();
        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/inventoryScene.fxml");

            pane = loader.load(fxmlStream);

            pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/sceneBuilder/css/inventory.css")).toExternalForm());

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  " + e);
        }
    }
}
