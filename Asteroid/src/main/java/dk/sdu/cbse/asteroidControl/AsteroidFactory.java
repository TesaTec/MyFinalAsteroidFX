package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.Asteroid;
import dk.sdu.cbse.common.components.*;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.LayerTypes;
import javafx.scene.paint.Color;

public class AsteroidFactory {

    public static Entity createAsteroid(float x, float y, int size, float rotation, float speed, int health) {
        Entity asteroid = new Asteroid();

        asteroid.addComponent(new TransformComponenet(x, y, rotation, size));
        asteroid.addComponent(new MovementComponent(speed));
        asteroid.addComponent(new GraphicsComponent(LayerTypes.ASTEROID, Color.SADDLEBROWN, -size, -size, size, -size, size, size, -size, size));
        asteroid.addComponent(new HealthComponent(health, true));
        asteroid.addComponent(new CollisionComponent(size));
        asteroid.addComponent(new AsteroidComponent());

        return asteroid;
    }
}
