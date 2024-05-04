package fr.robins.engine.gamelogic.displayable;

import java.util.ArrayList;
import java.util.List;

public class DisplayableSubject {
    List<DisplayableListObserver> observers = new ArrayList<>();
    List<Displayable> displayables = new ArrayList<>();

    /**
     * Get the current list of entity displayed, SHOULD NOT BE USED TO MODIFY THE LIST
     * @return
     */
    public List<Displayable> getDisplayables() {
        return displayables;
    }

    public void set(int index, Displayable displayable){
        this.displayables.set(index,displayable);
        notifyObservers();
    }

    public void setDisplayables(List<Displayable> displayables) {
        this.displayables = displayables;
        notifyObservers();
    }

    public void add(Displayable displayable) {
        displayables.add(displayable);
        notifyObservers();
    }
    public void remove(Displayable displayable) {
        displayables.remove(displayable);
        notifyObservers();
    }

    public void attach(DisplayableListObserver observer) {
        observers.add(observer);
    }

    public void detach(DisplayableListObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (DisplayableListObserver observer : observers) {
            observer.updateDisplayableList();
        }
    }
}
