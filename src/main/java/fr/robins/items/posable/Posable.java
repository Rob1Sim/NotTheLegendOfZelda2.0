package fr.robins.items.posable;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.engine.gamelogic.displayable.DisplayableSubject;
import fr.robins.entities.Player;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.types.Vector2D;
import javafx.scene.layout.Pane;

import java.util.List;

public abstract class Posable extends Item implements Collectable {
    public Posable(String name, String spritePath, Vector2D position, int price) {
        super(name, spritePath, position, price );
    }
    public abstract void use(Player player, DisplayableSubject displayablesEntity, Pane pane);
}
