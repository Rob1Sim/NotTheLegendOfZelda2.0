package fr.robins.engine;

import fr.robins.entities.Player;
import fr.robins.entities.enemy.CCEnemy;
import fr.robins.items.Inventory;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import fr.robins.types.entities.EntityType;
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
        //Initialisation
        player = new Player(new Vector2D((double) Utilities.WINDOW_WIDTH /2,(double) Utilities.WINDOW_HEIGHT /2));
        tileManager = new TileManager("/tiles/tilemap/grandeMap.xml");
        Pane backgroundPane = new Pane();
        CCEnemy ghost = new CCEnemy(EntityType.ENEMY_GHOST,TileManager.tilesToCoordinates(36,33), new Inventory());


        //Display tiles
        for (int i = 0; i < tileManager.getNumberOfLayers(); i++) {
            tileManager.draw(i,backgroundPane);
        }

        //Display player
        Node playerNode = player.draw();
        backgroundPane.getChildren().add(playerNode);
        Player.teleportPlayer(backgroundPane,TileManager.tilesToCoordinates(35,33));
        System.out.println("Player "+playerNode.getTranslateX() + " " + playerNode.getTranslateY());

        //Display Enemies
        Node ghostNode = ghost.draw();
        System.out.println("Ghost "+ghostNode.getTranslateX() + " " + ghostNode.getTranslateY());
        backgroundPane.getChildren().add(ghostNode);


        //Scene settings
        backgroundPane.setPrefSize(windowWidth, windowHeight);
        Scene scene = new Scene(backgroundPane);

        //Stage settings
        stage.setFullScreenExitHint("");
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