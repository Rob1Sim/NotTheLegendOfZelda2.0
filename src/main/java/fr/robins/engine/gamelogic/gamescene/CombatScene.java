package fr.robins.engine.gamelogic.gamescene;

import fr.robins.entities.Fighter;
import fr.robins.types.Utilities;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;


public class CombatScene extends GameScene{

    private final Fighter player;
    private final Fighter enemy;

    public CombatScene( Fighter player, Fighter enemy) {
        super();
        this.player = player;
        this.enemy = enemy;

        FXMLLoader loader = new FXMLLoader();
        try {
            InputStream fxmlStream = getClass().getResourceAsStream("/sceneBuilder/combatScene.fxml");
            Pane root = loader.load(fxmlStream);


            root.setPrefSize(Utilities.WINDOW_WIDTH,Utilities.WINDOW_HEIGHT);
            super.pane = root;

        } catch (IOException e) {
            throw new RuntimeException("Error have occured while loading the scene :  "+e);
        }


    }


}
