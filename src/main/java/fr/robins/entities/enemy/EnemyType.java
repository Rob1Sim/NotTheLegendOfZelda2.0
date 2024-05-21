package fr.robins.entities.enemy;


import fr.robins.items.Inventory;
import fr.robins.items.combat.spells.Spell;
import fr.robins.items.combat.spells.SpellType;
import fr.robins.items.combat.weapons.StealingWeapon;
import fr.robins.items.combat.weapons.WeaponItem;
import fr.robins.items.combat.weapons.WeaponType;

public enum EnemyType {

    MAGE("Mage", 20, 100, 5, 5, 5,
            new Spell[]{new Spell(SpellType.FIRE_BALL),
            new Spell(SpellType.HEAVEN_BLESSING),
            new Spell(SpellType.DIVINE_STRIKE),
            new Spell(SpellType.KALASH)},
            new Inventory(new WeaponItem(WeaponType.KNIFE)), 10,"/sprites/characters/c_dark_magician.png","VOUS .. NE ... PASSEREZ ... PAS ... *blurp*! "),
    GHOST("Fantome", 10, 30, 5, 5, 10, new Spell[]{new Spell(SpellType.LIGHTNING)}, new Inventory(new WeaponItem(WeaponType.GHOST_FIST)), 10, "/sprites/characters/c_white_ghost.png","Argh ! La magie ... mon seule point faible !"),
    RAT("Rat", 5, 2, 2, 5, 1, new Spell[]{}, new Inventory(new WeaponItem(WeaponType.RAT_CLAWS)), 1, "/sprites/characters/c_roman_brown.png","Argh ! L'argent ... je veux ... plus ... plus d'argent *blurp* !"),
    BOSS_MAGE("Roi démon", 100, 500, 10, 10, 15,
            new Spell[]{new Spell(SpellType.FIRE_BALL),
                    new Spell(SpellType.LIGHTNING),
                    new Spell(SpellType.DIVINE_STRIKE),
                    new Spell(SpellType.KALASH)},
            new Inventory(new WeaponItem(WeaponType.KNIFE)), 10,"/sprites/characters/c_dark_magician.png","VOUS .. NE ... PASSEREZ ... PAS ... *blurp*! "),
    ROMAN("Roman", 25, 2, 12, 15, 1, new Spell[]{}, new Inventory(new WeaponItem(WeaponType.RAT_CLAWS)), 10, "/sprites/characters/c_roman_brown.png","Argh ! L'argent ... je veux ... plus ... plus d'argent *blurp* !"),
    CASPER("Casper", 30, 30, 15, 15, 10, new Spell[]{new Spell(SpellType.LIGHTNING)}, new Inventory(new WeaponItem(WeaponType.GHOST_FIST)), 20, "/sprites/characters/c_white_ghost.png","Argh ! La magie ... mon seule point faible !"),
    CASPER_PICKPOCKET("Casper le pickpocket", 10, 30, 15, 15, 10, new Spell[]{}, new Inventory(new StealingWeapon(WeaponType.PICK_POCKET_GLOVES)), 20, "/sprites/characters/c_white_ghost.png","Argh ! La magie ... mon seule point faible !"),

    ;




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
