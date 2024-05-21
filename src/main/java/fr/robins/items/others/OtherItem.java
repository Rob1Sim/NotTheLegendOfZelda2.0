package fr.robins.items.others;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.items.Collectable;
import fr.robins.items.Item;
import fr.robins.types.Vector2D;
import fr.robins.world.TileManager;

public class OtherItem extends Item implements Displayable, Collectable {
    public OtherItem(String name, String spritePath, Vector2D position, int price) {
        super(name, spritePath, position, price);
    }
    public OtherItem(OtherItemType otherItemType, Vector2D position){
        super(otherItemType.getName(),otherItemType.getSprite(),position,otherItemType.getCost());
    }

    public static OtherItem otherItemGenerator(OtherItemType otherItemType, int column, int row) {
        return new OtherItem(otherItemType, TileManager.tilesToCoordinates(column,row));
    }
}
