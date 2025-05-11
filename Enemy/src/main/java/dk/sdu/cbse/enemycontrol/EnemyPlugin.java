package dk.sdu.cbse.enemycontrol;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.services.IGamePluginService;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

import dk.sdu.cbse.common.enemy.Enemy;
import javafx.scene.paint.Color;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService{

    private Entity enemy;
    @Override
    public void start(GameData gameData, World world) {
        int max = 5;
        for(int i = 0; i < max; i++) {
            enemy = createEnemy(i);
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity entity: world.getEntities()) {
            if(entity.getClass() == Enemy.class) {
                world.removeEntity(entity);
            }
        }
    }
        private Entity createEnemy(int iteration) {
            Entity enemy = new Enemy();

            Random randomSpeed = new Random();

            enemy.setLayer(LayerTypes.ENEMY);
            enemy.setPolygonCoordinates(-15,-10,15,0,-15,10, -5,0);
            enemy.setRadius(10);

            enemy.setSpeed(randomSpeed.nextDouble(1.5) + 1);
            enemy.setEntityColor(Color.RED);
            enemy.setHealth(3);

            enemy.setX(150 * iteration);
            enemy.setY(50);

            return enemy;
        }
}
