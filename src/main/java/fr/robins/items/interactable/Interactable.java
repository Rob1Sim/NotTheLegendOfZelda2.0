package fr.robins.items.interactable;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.items.Item;

public abstract class Interactable extends Item implements Displayable {
    public Interactable(String name) {
        super(name);
    }

}
