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


    @Override
    public void start(GameData gameData, World world) {
        Random randomSpeed = new Random();

        int max = 5;
        for(int i = 0; i < max; i++) {
             world.addEntity(EnemyFactory.createEnemy
                     (150 * i, 50, 0, 10, (float)randomSpeed.nextDouble(1.5) + 1, 3)
             );
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

}
