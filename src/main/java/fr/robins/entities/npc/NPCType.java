package fr.robins.entities.npc;

import fr.robins.items.Inventory;

public enum NPCType {
    NPC_BLACK_SMITH("Le forgeron", 10, 10, 10, 5, 10,new Inventory(), 100,  "/sprites/characters/c_black_smith.png", "Je te salut aventurier ! Ma forge est toujours prête à servir !"),
    NPC_TRADER("Le marchand", 10, 5, 5, 5, 100, new Inventory(), 100, "/sprites/characters/c_trader.png"," Bonsoir aventurier ! La M-16 56Mn enchanté est à -50% aujourd'hui !"),
    NPC_WOMAN("Chara", 10, 5, 5, 5, 10,new Inventory(), 100,  "/sprites/characters/c_princess.png","Je te vois."),
    NPC_OLD_WOMAN("Mémé", 10, 5, 5, 5, 10,new Inventory(), 100,  "/sprites/characters/c_old_woman.png","Ce visage ! Serait-tu l'enfant de Jurons ?"),
    NPC_VIKING("Hydyr", 10, 15, 15, 5, 10,new Inventory(), 100,  "/sprites/characters/c_viking.png","MY MOTHER TOLD ME, SOMEDAY I WILL BUY GALLEYS WITH GOOD OARS SAILS TO DISTANT SHORES ... (cette personne chante très faux)");

    private final String name;
    private final int hp;
    private final int mana;
    private final int constitution;
    private final int dexterity;
    private final int strength;
    private final Inventory inventory;
    private final int money;
    private final String sprite;
    private final String dialogueText;

    NPCType(String name, int hp, int mana, int constitution, int strength, int dexterity, Inventory inventory, int money,  String sprite, String dialogueText) {
        this.name = name;
        this.hp = hp;
        this.mana = mana;
        this.constitution = constitution;
        this.strength = strength;
        this.dexterity = dexterity;
        this.inventory = inventory;
        this.money = money;
        this.sprite = sprite;
        this.dialogueText = dialogueText;
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

    public String getDialogueText() {
        return dialogueText;
    }
}
