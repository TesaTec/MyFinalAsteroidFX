package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    @Override
    public void start(GameData gameData, World world) {

        int max = 20;
        for(int i = 0; i < max; i++) {
            asteroid = createAsteroid(gameData, i, max);
            world.addEntity(asteroid);
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

    private Entity createAsteroid(GameData gameData, int iteration, int max) {
        Entity asteroid = new Asteroid();
        Random range = new Random();


        int size = range.nextInt(10)+5;
        asteroid.setPolygonCoordinates(-size, -size, size, -size, size, size, -size, size);

        asteroid.setRadius(size);
        asteroid.setEntityColor(Color.SADDLEBROWN);
        asteroid.setLayer(LayerTypes.ASTEROID);
        asteroid.setSpeed(range.nextDouble(2)+ 0.5);
        asteroid.setHealth(2);


        switch(iteration) {
            case 0, 1, 2, 3, 4:
                asteroid.setX(0);
                asteroid.setY(0);

                asteroid.setRotation(range.nextInt(90));
                break;
            case 5, 6, 7, 8, 9:
                asteroid.setX(gameData.getDisplayWidth());
                asteroid.setY(0);

                asteroid.setRotation(range.nextInt(180) + 90);

                break;
            case 10, 11, 12, 13, 14:
                asteroid.setX(0);
                asteroid.setY(gameData.getDisplayHeight());

                asteroid.setRotation(range.nextInt(90) * -1);
                break;
            case 15, 16, 17, 18, 19:
                asteroid.setX(gameData.getDisplayWidth());
                asteroid.setY(gameData.getDisplayHeight());

                asteroid.setRotation((range.nextInt(180) + 90) * -1);
                break;

        }
        return asteroid;
    }

}