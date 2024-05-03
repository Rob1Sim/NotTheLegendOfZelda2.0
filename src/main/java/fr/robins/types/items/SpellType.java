package fr.robins.types.items;

import fr.robins.types.entities.CharacType;
import fr.robins.types.entities.EntityType;


public enum SpellType {
    BITES_THE_DUST("Bites the dust", 30, 10, CharacType.HP, EntityType.ENEMY),
    LIGHTNING("Eclair", 16, 15, CharacType.HP,EntityType.ENEMY),
    REQUIEM("Gold Experience Requiem", 15, 30, CharacType.CONSTITUTION,EntityType.PLAYER),
    KILLER_QUEEN("Killer Queen",25,15, CharacType.HP,EntityType.ENEMY),
    HERMIT_PURPLE("Hermit Purple",30,30, CharacType.HP,EntityType.ENEMY),
    HEAL("Régénération Vitale", 20,10, CharacType.HP, EntityType.PLAYER),
    HEAVEN_BLESSING("Made In Heaven", 10,10, CharacType.STRENGTH, EntityType.PLAYER),

    ;

    private final String name;
    private final int modifier;
    private final int manaCost;
    private final CharacType characType;
    private final EntityType entityType;

    /**
     *
     * @param modifier the value to increase/decrease
     * @param characType the characteristic to add the modifcator
     * @param entityType The type of entity to target
     */
    SpellType(String name, int modifier, int manaCost, CharacType characType, EntityType entityType) {
        this.name = name;
        this.modifier = modifier;
        this.manaCost = manaCost;
        this.characType = characType;
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public int getModifier() {
        return modifier;
    }

    public int getManaCost() {
        return manaCost;
    }

    public CharacType getCaracType() {
        return characType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
