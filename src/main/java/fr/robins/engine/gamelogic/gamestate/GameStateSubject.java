package fr.robins.engine.gamelogic.gamestate;

import java.util.ArrayList;
import java.util.List;

public class GameStateSubject {

    private final List<GameStateObserver> observers = new ArrayList<GameStateObserver>();
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyObservers();
    }

    public void attach(GameStateObserver observer) {
        observers.add(observer);
    }
    public void detach(GameStateObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (GameStateObserver observer : observers) {
            observer.updateGameState();
        }
    }
}
