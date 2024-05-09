package fr.robins.items.consumable.potions;


import fr.robins.entities.entitiestype.CharacType;

public enum PotionType {
    HEAL_POTION("Potion de soin",25, CharacType.HP,false,"/sprites/items/red_potion.png",30),
    MANA_POTION("Potion de mana",50, CharacType.MANA,false,"/sprites/items/blue_potion.png",30),
    SPEED_POTION("Potion de vitesse",10, CharacType.DEXTERITY,false,"/sprites/items/green_potion.png",30),
    ;

    private final String name;
    private final int modificator;
    private final CharacType characType;
    private final boolean useHasWeapon;
    private final String spritePath;
    private final int price;

    PotionType(String name, int modificator, CharacType characType, boolean useHasWeapon, String spritePath, int price) {
        this.name = name;
        this.modificator = modificator;
        this.characType = characType;
        this.useHasWeapon = useHasWeapon;
        this.spritePath = spritePath;
        this.price = price;
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

    public int getPrice() {
        return price;
    }
}
