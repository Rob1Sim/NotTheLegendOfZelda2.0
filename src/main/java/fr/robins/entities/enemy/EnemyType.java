package fr.robins.entities.enemy;


import fr.robins.items.Inventory;
import fr.robins.items.combat.spells.Spell;
import fr.robins.items.combat.spells.SpellType;

public enum EnemyType {

    MAGE("Mage", 20, 100, 5, 5, 5,
            new Spell[]{new Spell(SpellType.BITES_THE_DUST),
            new Spell(SpellType.HEAVEN_BLESSING),
            new Spell(SpellType.KILLER_QUEEN),
            new Spell(SpellType.REQUIEM)},
            new Inventory(), 10,"/sprites/characters/c_dark_magician.png","VOUS .. NE ... PASSEREZ ... PAS ... *blurp*! "),
    ENEMY_GHOST("Ghost", 10, 5, 5, 5, 10, new Spell[]{}, new Inventory(), 10, "/sprites/characters/c_white_ghost.png","*Argh* La magie ... mon seule point faible !"),
    ENEMY_RAT("Rat", 5, 2, 2, 5, 1, new Spell[]{}, new Inventory(), 1, "/sprites/characters/c_roman_brown.png","*Argh* L'argent ... plus ... plus d'argent *blurp* !"),;



    private final String name;
    private final int hp;
    private final int mana;
    private final int constitution;
    private final int strength;
    private final int dexterity;
    private final Spell[] spells;
    private final Inventory inventory;
    private final int money;
    private final String sprite;
    private final String deathPhrase;


    EnemyType(String name, int hp, int mana, int constitution, int strength, int dexterity, Spell[] spells, Inventory inventory, int money, String sprite, String deathPhrase){
        this.name = name;
        this.hp = hp;
        this.mana = mana;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.spells = spells;
        this.inventory = inventory;
        this.money = money;
        this.sprite = sprite;
        this.deathPhrase = deathPhrase;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMana() {
        return mana;
    }

    public int getMoney() {
        return money;
    }

    public String getDeathPhrase() {
        return deathPhrase;
    }

    public String getSprite() {
        return sprite;
    }
}
