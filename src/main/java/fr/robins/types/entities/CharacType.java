package fr.robins.types.entities;

public enum CharacType {
    HP("Point de vie"),
    MANA("Mana"),
    CONSTITUTION("Constitution"),
    STRENGTH("Force"),
    DEXTERITY("Dextérité"),
    ;

    private final String name;
    CharacType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
