package fr.robins.items.interactable;

import fr.robins.engine.Displayable;
import fr.robins.items.Item;
import javafx.scene.Node;

public abstract class Interactable extends Item implements Displayable {
    public Interactable(String name) {
        super(name);
    }

}
