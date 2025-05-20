package dk.sdu.cbse.enemycontrol;

import dk.sdu.cbse.common.components.*;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.enemy.Enemy;
import javafx.scene.paint.Color;


public class EnemyFactory {

    public static Entity createEnemy(float x, float y,float rotation, int radius, float speed, int health) {
        Entity enemy = new Enemy();

        enemy.addComponent(new TransformComponenet(x, y, rotation, radius));
        enemy.addComponent(new MovementComponent(speed));
        enemy.addComponent(new GraphicsComponent(LayerTypes.ENEMY, Color.RED, -15,-10,15,0,-15,10, -5,0));
        enemy.addComponent(new HealthComponent(health, true));
        enemy.addComponent(new CollisionComponent(radius));
        enemy.addComponent(new AttackComponent(90, 0, 0));

        enemy.addComponent(new EnemyComponent());



        return enemy;
    }
}
