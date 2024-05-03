package fr.robins.engine.controller;

import fr.robins.engine.GameState;
import fr.robins.engine.Inputs;
import fr.robins.engine.collisions.CollisionManager;
import fr.robins.entities.Player;
import fr.robins.types.Utilities;
import fr.robins.types.Vector2D;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameController {

    private Player player;
    private Pane root;
    private Stage stage;
    private SceneController sceneController;
    private GameState gameState;

    public GameController(Stage stage) {
        this.stage = stage;
    }

    public void init(){
        //Initialisation
        gameState = GameState.START;

        sceneController = new SceneController(this);

        root = new VBox(10);
        Label title = new Label("Not the legend of zelda !");
        Button startButton = new Button("Commencer");
        Button leaveButton = new Button("Quitter");

        player = new Player(new Vector2D(Utilities.WINDOW_WIDTH /2,Utilities.WINDOW_HEIGHT /2));

        //Button listener
        startButton.setOnAction(actionEvent -> {
            sceneController.switchToGameScene(actionEvent);
        });

        leaveButton.setOnAction(actionEvent -> {
            stage.close();
        });


        //Scene settings
        root.setPrefSize(Utilities.WINDOW_WIDTH, Utilities.WINDOW_HEIGHT);

        //Start Menu
        /**
        root.setPadding(new Insets(10));
        root.getChildren().addAll(title,startButton, leaveButton);

        Scene scene = new Scene(root);
        **/


        //Stage settings
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
        stage.setTitle("Not the legend of zelda");
        sceneController.test(stage);
        //stage.setScene(scene);
        //stage.show();
    }

    public void update(){
        if (gameState != GameState.DEAD && player.getHp() <= 0){
            gameState = GameState.DEAD;
        }
        switch (gameState){
            case START:
                break;
            case WALKING:
                Inputs.handleMovementInput(sceneController.getCurrentScene(),player, sceneController.getPane(),stage);
                CollisionManager.environmentCollisionChecker(player,sceneController.getTileManager());
                break;
            case WIN:
                break;
            case COMBAT:
                Inputs.handleArrowInput(sceneController.getCurrentScene());
                break;
            case DEAD:
                break;
        }
    }



    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getPlayer() {
        return player;
    }
}
