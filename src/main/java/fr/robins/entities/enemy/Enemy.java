package fr.robins.entities.enemy;

import fr.robins.engine.gamelogic.displayable.Displayable;
import fr.robins.entities.Fighter;
import fr.robins.items.combat.AttackType;
import fr.robins.types.Vector2D;

public class Enemy extends Fighter implements Displayable {
    private final EnemyType enemyType;
    private final String deathPhrase;
    public Enemy(EnemyType enemyType, Vector2D worldPosition) {
        super(enemyType.getName(), enemyType.getHp(), enemyType.getMana(), enemyType.getConstitution(), enemyType.getStrength(), enemyType.getDexterity(),enemyType.getMoney(),worldPosition,enemyType.getSprite(), enemyType.getSpells());
        setInventory(enemyType.getInventory());
        this.enemyType = enemyType;
        this.deathPhrase = enemyType.getDeathPhrase();
    }

    @Override
    public void takeDamage(int damage, boolean isDodgeable,boolean alwaysDodge ,AttackType attackType) {
        if (enemyType == EnemyType.ENEMY_GHOST && attackType == AttackType.BODILICAL){
            alwaysDodge = true;
        }
        super.takeDamage(damage, isDodgeable,alwaysDodge,attackType);

        switch (enemyType){
            case ENEMY_GHOST -> {
                if (alwaysDodge){
                    setTextToDisplay("Imbécile je suis un fantôme, tu pensais pouvoir me toucher ??");
                }
            }
        }

    }

    public String getDeathPhrase() {
        return deathPhrase;
    }
}
