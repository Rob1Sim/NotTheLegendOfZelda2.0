package fr.robins.items.combat.spells;

import fr.robins.entities.entitiestype.CharacType;
import fr.robins.entities.entitiestype.EntityType;


public enum SpellType {
    FIRE_BALL("Boule de feu supreme", 30, 10, CharacType.HP, EntityType.ENEMY),
    LIGHTNING("Eclair", 16, 15, CharacType.HP,EntityType.ENEMY),
    BOOST_CONST("Résistance draconique", 15, 30, CharacType.CONSTITUTION,EntityType.PLAYER),
    DIVINE_STRIKE("Frappe divine",25,15, CharacType.HP,EntityType.ENEMY),
    KALASH("Kalashnikov occulte",30,30, CharacType.HP,EntityType.ENEMY),
    HEAL("Régénération Vitale", 20,10, CharacType.HP, EntityType.PLAYER),
    HEAVEN_BLESSING("Bénédiction des dieux", 10,10, CharacType.STRENGTH, EntityType.PLAYER),

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
