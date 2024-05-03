package fr.robins.types.entities;

import fr.robins.items.Inventory;
import fr.robins.types.Vector2D;

public enum NPCType {
    NPC_BLACK_SMITH("Black Smith", 10, 10, 10, 5, 10,new Inventory(), 100,  "/sprites/characters/c_black_smith.png"),
    NPC_TRADER("Black Smith", 10, 5, 5, 5, 100, new Inventory(), 100, "/sprites/characters/c_trader.png"),
    NPC_WOMAN("Woman", 10, 5, 5, 5, 10,new Inventory(), 100,  "/sprites/characters/c_princess.png"),
    NPC_OLD_WOMAN("Old Woman", 10, 5, 5, 5, 10,new Inventory(), 100,  "/sprites/characters/c_old_woman.png"),
    NPC_VIKING("Viking", 10, 15, 15, 5, 10,new Inventory(), 100,  "/sprites/characters/c_viking.png");

    private final String name;
    private final int hp;
    private final int mana;
    private final int constitution;
    private final int dexterity;
    private final int strength;
    private final Inventory inventory;
    private final int money;
    private final String sprite;

    NPCType(String name, int hp, int mana, int constitution, int strength, int dexterity, Inventory inventory, int money,  String sprite) {
        this.name = name;
        this.hp = hp;
        this.mana = mana;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.inventory = inventory;
        this.money = money;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMoney() {
        return money;
    }

    public String getSprite() {
        return sprite;
    }
}
