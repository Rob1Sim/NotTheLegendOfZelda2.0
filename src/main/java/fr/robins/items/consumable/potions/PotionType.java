package fr.robins.items.consumable.potions;


import fr.robins.entities.entitiestype.CharacType;

public enum PotionType {
    HEAL_POTION("Potion de soin",25, CharacType.HP,false,"/sprites/items/red_potion.png"),
    MANA_POTION("Potion de mana",25, CharacType.MANA,false,"/sprites/items/blue_potion.png"),
    SPEED_POTION("Potion de vitesse",10, CharacType.DEXTERITY,false,"/sprites/items/green_potion.png"),
    ;

    private final String name;
    private final int modificator;
    private final CharacType characType;
    private final boolean useHasWeapon;
    private final String spritePath;

    PotionType(String name, int modificator, CharacType characType, boolean useHasWeapon, String spritePath) {
        this.name = name;
        this.modificator = modificator;
        this.characType = characType;
        this.useHasWeapon = useHasWeapon;
        this.spritePath = spritePath;
    }

    public String getName() {
        return name;
    }

    public int getModificator() {
        return modificator;
    }

    public CharacType getCharacType() {
        return characType;
    }

    public boolean isUseHasWeapon() {
        return useHasWeapon;
    }

    public String getSpritePath() {
        return spritePath;
    }
}
