package fr.robins.types.items;

public enum WeaponType {
    AXE("Hache",20),
    DOUBLE_AXE("Hache",25),
    KNIFE("Couteau",5),
    SWORD("Epée",15);

    private final String name;
    private final int damage;
    WeaponType(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
