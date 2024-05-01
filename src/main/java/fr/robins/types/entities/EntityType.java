package fr.robins.types.entities;

public enum EntityType {
    ENEMY_MAGE("Mage", 50, 1, 5, 15, 20, "/sprites/characters/c_dark_magician.png"),
    ENEMY_GHOST("Ghost", 10, 5, 5, 5, 10, "/sprites/characters/c_white_ghost.png"),
    ENEMY_RAT("Rat", 5, 2, 2, 5, 1, "/sprites/characters/c_roman_brown.png"),
    NPC_BLACK_SMITH("Black Smith", 10, 10, 10, 5, 10, "/sprites/characters/c_black_smith.png"),
    NPC_TRADER("Black Smith", 10, 5, 5, 5, 100, "/sprites/characters/c_trader.png"),
    NPC_WOMAN("Woman", 10, 5, 5, 5, 10, "/sprites/characters/c_princess.png"),
    NPC_OLD_WOMAN("Old Woman", 10, 5, 5, 5, 10, "/sprites/characters/c_old_woman.png"),
    NPC_VIKING("Viking", 10, 15, 15, 5, 10, "/sprites/characters/c_viking.png");


    private final String name;
    private final double hp;
    private final int strength;
    private final int constitution;
    private final double range;
    private final int money;
    private final String spritePath;
    EntityType(String name, double hp, int strength, int constitution, double range, int money, String spritePath) {
        this.hp = hp;
        this.name = name;
        this.strength = strength;
        this.constitution = constitution;
        this.range = range;
        this.money = money;
        this.spritePath = spritePath;
    }

    public String getName() {
        return this.name;
    }
    public double getHp() {
        return this.hp;
    }
    public int getStrength() {
        return this.strength;
    }
    public int getConstitution() {
        return this.constitution;
    }
    public double getRange() {
        return this.range;
    }
    public int getMoney() {
        return this.money;
    }
    public String getSpritePath() {
        return this.spritePath;
    }
}
