package fr.robins.engine.gamelogic.gamescene;

import java.util.ArrayList;
import java.util.List;

public class GameSceneSubject {

    private final List<GameSceneObserver> observers = new ArrayList<GameSceneObserver>();
    private GameScene gameScene;


    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
        notifyObservers();
    }

    public void attach(GameSceneObserver observer) {
        observers.add(observer);
    }
    public void detach(GameSceneObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (GameSceneObserver observer : observers) {
            observer.updateGameScene();
        }
    }
}
