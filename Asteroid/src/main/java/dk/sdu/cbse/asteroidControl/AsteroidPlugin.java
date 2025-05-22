package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.Asteroid;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class AsteroidPlugin implements IGamePluginService {


    @Override
    public void start(GameData gameData, World world) {
        int max = 20;
        Random rand = new Random();

        for (int i = 0; i < max; i++) {
            float x = rand.nextBoolean() ? 0 : gameData.getDisplayWidth();
            float y = rand.nextBoolean() ? 0 : gameData.getDisplayHeight();
            float speed = rand.nextFloat() * 2 + 0.5f;
            float rotation = rand.nextFloat() * 360;
            int size = rand.nextInt(10) + 5;
            int pieces;

            if(size >= 12) {
                pieces = 3;
            } else if(size >= 8){
                pieces = 2;
            } else {
                pieces = 0;
            }
            int health = 2;

            world.addEntity(AsteroidFactory.createAsteroid(x, y, size, rotation, speed, health, pieces));

        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            if(entity.getClass() == Asteroid.class) {
                world.removeEntity(entity);
            }
        }
    }
}