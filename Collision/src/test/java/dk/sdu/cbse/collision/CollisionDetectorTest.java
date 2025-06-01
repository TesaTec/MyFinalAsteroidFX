package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.components.CollisionComponent;
import dk.sdu.cbse.common.components.GraphicsComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setup() {
        collisionDetector = new CollisionDetector();
        gameData = new GameData();
        world = new World();
    }

    @Test
    public void testEntitiesCollide() {
        Entity e1 = new Entity();
        e1.addComponent(new TransformComponenet(100,100, 0, 5));
        e1.addComponent(new GraphicsComponent(LayerTypes.PLAYER, Color.LIGHTBLUE, -15,-10,15,0,-15,10, -5,0));
        e1.addComponent(new HealthComponent(1, true));
        e1.addComponent(new CollisionComponent(10));


        Entity e2 = new Entity();
        e2.addComponent(new TransformComponenet(105,100, 0, 5));
        e2.addComponent(new GraphicsComponent(LayerTypes.ENEMY, Color.RED, -15,-10,15,0,-15,10, -5,0));
        e2.addComponent(new HealthComponent(1, true));
        e2.addComponent(new CollisionComponent(10));


        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);


        // Entities should be removed after collision
        assertTrue(e1.getComponent(CollisionComponent.class).isCollided());
        assertTrue(e2.getComponent(CollisionComponent.class).isCollided());
    }

    @Test
    public void testEntitiesTooFarApartNoCollision() {
        Entity e1 = new Entity();
        e1.addComponent(new TransformComponenet(100,100, 0, 5));
        e1.addComponent(new GraphicsComponent(LayerTypes.PLAYER, Color.LIGHTBLUE, -15,-10,15,0,-15,10, -5,0));
        e1.addComponent(new HealthComponent(1, true));
        e1.addComponent(new CollisionComponent(10));



        Entity e2 = new Entity();
        e2.addComponent(new TransformComponenet(200,100, 0, 5));
        e2.addComponent(new GraphicsComponent(LayerTypes.ENEMY, Color.RED, -15,-10,15,0,-15,10, -5,0));
        e2.addComponent(new HealthComponent(1, true));
        e2.addComponent(new CollisionComponent(10));


        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);


        // Entities should not collide
        assertFalse(e1.getComponent(CollisionComponent.class).isCollided());
        assertFalse(e2.getComponent(CollisionComponent.class).isCollided());
    }

    @Test
    public void testBackgroundLayerIgnored() {
        Entity e1 = new Entity();
        e1.addComponent(new TransformComponenet(100,100, 0, 5));
        e1.addComponent(new GraphicsComponent(LayerTypes.BACKGROUND, Color.LIGHTBLUE, -15,-10,15,0,-15,10, -5,0));
        e1.addComponent(new HealthComponent(1, true));
        e1.addComponent(new CollisionComponent(10));


        Entity e2 = new Entity();
        e2.addComponent(new TransformComponenet(100,100, 0, 5));
        e2.addComponent(new GraphicsComponent(LayerTypes.ENEMY, Color.RED, -15,-10,15,0,-15,10, -5,0));
        e2.addComponent(new HealthComponent(1, true));
        e2.addComponent(new CollisionComponent(10));

        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);


        // No collision should be detected
        assertFalse(e1.getComponent(CollisionComponent.class).isCollided());
        assertFalse(e2.getComponent(CollisionComponent.class).isCollided());
    }

    @Test
    public void testEnemyAsteroidIgnored() {
        Entity e1 = new Entity();
        e1.addComponent(new TransformComponenet(100,100, 0, 5));
        e1.addComponent(new GraphicsComponent(LayerTypes.ENEMY, Color.LIGHTBLUE, -15,-10,15,0,-15,10, -5,0));
        e1.addComponent(new HealthComponent(1,  true));
        e1.addComponent(new CollisionComponent(10));



        Entity e2 = new Entity();
        e2.addComponent(new TransformComponenet(100,100, 0, 5));
        e2.addComponent(new GraphicsComponent(LayerTypes.ASTEROID, Color.RED, -15,-10,15,0,-15,10, -5,0));
        e2.addComponent(new HealthComponent(1, true));
        e2.addComponent(new CollisionComponent(10));



        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);


        // No collision should be detected
        assertFalse(e1.getComponent(CollisionComponent.class).isCollided());
        assertFalse(e2.getComponent(CollisionComponent.class).isCollided());
    }

}
