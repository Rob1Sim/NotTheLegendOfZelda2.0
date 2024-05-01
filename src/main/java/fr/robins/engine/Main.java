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
    double windowWidth = Utilities.WINDOW_WIDTH , windowHeight = Utilities.WINDOW_HEIGHT ;


    @Override
    public void start(Stage stage) throws Exception {
        player = new Player(new Vector2D((double) Utilities.WINDOW_WIDTH /2,(double) Utilities.WINDOW_HEIGHT /2));
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        Pane backgroundPane = new Pane();



        //Display tiles
        for (int i = 0; i < tileManager.getNumberOfLayers(); i++) {
            tileManager.draw(i,backgroundPane);
        }

        //Display player
        Node playerNode = player.draw();



        //Scene settings
        backgroundPane.getChildren().add(playerNode);

        Player.teleportPlayer(backgroundPane,new Vector2D(3364,3364));

        backgroundPane.setPrefSize(windowWidth, windowHeight );


        Scene scene = new Scene(backgroundPane);

        //Utilities.setFullScreen(stage);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Not the legend of zelda");
        stage.show();

        //GameLoop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Inputs.handleKeyInput(scene,player, backgroundPane,stage);
            }
        };
        gameLoop.start();
    }
}