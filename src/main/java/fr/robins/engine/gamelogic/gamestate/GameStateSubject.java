package fr.robins.engine.gamelogic.gamestate;

import fr.robins.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class GameStateSubject {

    private final List<GameStateObserver> observers = new ArrayList<GameStateObserver>();
    private GameState gameState;
    private Entity boss;

    public GameState getGameState() {
        return gameState;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        notifyObservers();
    }

    //Condition de victoire
    public boolean isGameWin() {
        return boss != null && boss.getHp() <= 0;
    }


    public Entity getBoss() {
        return boss;
    }

    public void setBoss(Entity boss) {
        this.boss = boss;
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
