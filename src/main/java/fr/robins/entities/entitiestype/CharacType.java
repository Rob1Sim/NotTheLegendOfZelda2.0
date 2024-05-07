package fr.robins.entities.entitiestype;

public enum CharacType {
    HP("Point de vie"),
    MANA("Mana"),
    CONSTITUTION("Constitution"),
    STRENGTH("Force"),
    DEXTERITY("Dextérité"),
    MONEY("GOLD"),
    ;

    private final String name;
    CharacType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
