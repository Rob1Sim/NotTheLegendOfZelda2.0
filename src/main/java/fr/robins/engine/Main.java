package fr.robins.engine;

import fr.robins.engine.collisions.CollisionManager;
import fr.robins.entities.Entity;
import fr.robins.entities.Player;

import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;

import fr.robins.world.TileManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    //Relations
    private Player player;



    @Override
    public void start(Stage stage) {
        //Initialisation
        player = new Player(new Vector2D(Utilities.WINDOW_WIDTH /2,Utilities.WINDOW_HEIGHT /2));
        TileManager tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        Pane backgroundPane = new Pane();



        //Display tiles
        for (int i = 0; i < tileManager.getNumberOfLayers(); i++) {
            tileManager.draw(i,backgroundPane);
        }

        //Display player

        Entity.renderEntity(player,backgroundPane);
        Player.teleportPlayer(backgroundPane,TileManager.tilesToCoordinates(35,33));

        //Display Enemies



        //Scene settings
        backgroundPane.setPrefSize(Utilities.WINDOW_WIDTH, Utilities.WINDOW_HEIGHT);
        Scene scene = new Scene(backgroundPane);

        //Stage settings
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.setTitle("Not the legend of zelda");
        stage.show();

        //GameLoop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Inputs.handleKeyInput(scene,player, backgroundPane,stage);
                CollisionManager.environmentCollisionChecker(player,tileManager);
            }
        };
        gameLoop.start();
    }
}