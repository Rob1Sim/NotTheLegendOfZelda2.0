package fr.robins.engine;

import fr.robins.entities.Player;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Relations
    private Player player;
    private TileManager tileManager;

    //Widows parameters
    int windowWidth = Utilities.WINDOW_WIDTH , windowHeight = Utilities.WINDOW_HEIGHT ;


    @Override
    public void start(Stage stage) throws Exception {
        player = new Player(new Vector2D(0*Utilities.TILE_SIZE,0*Utilities.TILE_SIZE));
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        Pane pane = new Pane();

        pane.setPrefSize(windowWidth, windowHeight );


        //Display tiles
        for (int i = 0; i < tileManager.getNumberOfLayers(); i++) {
            tileManager.draw(i,pane);
        }

        //Display player
        Node playerNode = player.draw();

        playerNode.setTranslateX(player.getPosition().getX());
        playerNode.setTranslateY(player.getPosition().getY());

        pane.getChildren().add(playerNode);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Not the legend of zelda");
        stage.show();

        //GameLoop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Player.handlePlayerInput(scene,player);
            }
        };
        gameLoop.start();
    }
}