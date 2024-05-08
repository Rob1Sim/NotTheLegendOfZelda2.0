package fr.robins.items.interactable.destructible;

public enum DestructibleType {
    TABLE("Table","/tiles/tile_0072.png"),
    CHAIR("Chair","/tiles/tile_0073.png"),
    CRATE("Crate","/tiles/tile_0063.png"),
    BARREL("Barrel","/tiles/tile_0082.png"),
    CHEST("Chest","/tiles/tile_0089.png");

    private final String name;
    private final String path;
    private DestructibleType(final String name, final String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }


}
