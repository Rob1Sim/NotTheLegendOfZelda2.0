package fr.robins.entities.enemy;


import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Fighter;
import fr.robins.types.Vector2D;

public class Enemy extends Fighter implements Displayable {
    public Enemy(EnemyType enemyType, Vector2D worldPosition) {
        super(enemyType.getName(), enemyType.getHp(), enemyType.getMana(), enemyType.getConstitution(), enemyType.getStrength(), enemyType.getDexterity(),enemyType.getMoney(),worldPosition,enemyType.getSprite(), enemyType.getSpells());
        setInventory(enemyType.getInventory());
    }
}
