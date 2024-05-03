package fr.robins.types.items;


import fr.robins.types.entities.CharacType;

public enum PotionType {
    HEAL_POTION("Potion de soin",25, CharacType.HP,false),
    MANA_POTION("Potion de mana",25, CharacType.MANA,false),
    SPEED_POTION("Potion de vitesse",10, CharacType.DEXTERITY,false),
    ;

    private final String name;
    private final int modificator;
    private final CharacType characType;
    private final boolean useHasWeapon;

    PotionType(String name, int modificator, CharacType characType, boolean useHasWeapon) {
        this.name = name;
        this.modificator = modificator;
        this.characType = characType;
        this.useHasWeapon = useHasWeapon;
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
}
