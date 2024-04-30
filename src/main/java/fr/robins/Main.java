package fr.robins;

import fr.robins.entities.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Player player;

    @Override
    public void start(Stage stage) throws Exception {
        player = new Player();

        Pane pane = new Pane();

        pane.getChildren().add(player.draw());

        Scene scene = new Scene(pane, 600,400);

        stage.setScene(scene);
        stage.setTitle("Not the legend of zelda");
        stage.show();

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Boucle de gameplay
                Player.handlePlayerInput(scene,player);

            }
        };
        gameLoop.start();
    }
}