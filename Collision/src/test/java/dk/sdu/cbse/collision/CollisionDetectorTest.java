package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
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
    public void testEntitiesCollideAndAreRemoved() {
        Entity e1 = new Entity();
        e1.setX(100);
        e1.setY(100);
        e1.setRadius(10);
        e1.setLayer(LayerTypes.PLAYER);

        Entity e2 = new Entity();
        e2.setX(105); // within collision distance
        e2.setY(100);
        e2.setRadius(10);
        e2.setLayer(LayerTypes.ENEMY);

        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);

        // Entities should be removed after collision
        assertFalse(world.getEntities().contains(e1));
        assertFalse(world.getEntities().contains(e2));
    }

    @Test
    public void testEntitiesTooFarApartNoCollision() {
        Entity e1 = new Entity();
        e1.setX(100);
        e1.setY(100);
        e1.setRadius(10);
        e1.setLayer(LayerTypes.PLAYER);

        Entity e2 = new Entity();
        e2.setX(200); // far enough to not collide
        e2.setY(100);
        e2.setRadius(10);
        e2.setLayer(LayerTypes.ENEMY);

        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);

        // Entities should remain
        assertTrue(world.getEntities().contains(e1));
        assertTrue(world.getEntities().contains(e2));
    }

    @Test
    public void testBackgroundLayerIgnored() {
        Entity e1 = new Entity();
        e1.setX(100);
        e1.setY(100);
        e1.setRadius(10);
        e1.setLayer(LayerTypes.BACKGROUND);

        Entity e2 = new Entity();
        e2.setX(100);
        e2.setY(100);
        e2.setRadius(10);
        e2.setLayer(LayerTypes.PLAYER);

        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);

        // No collision should be detected
        assertTrue(world.getEntities().contains(e1));
        assertTrue(world.getEntities().contains(e2));
    }

    @Test
    public void testEnemyAsteroidIgnored() {
        Entity e1 = new Entity();
        e1.setX(100);
        e1.setY(100);
        e1.setRadius(10);
        e1.setLayer(LayerTypes.ENEMY);

        Entity e2 = new Entity();
        e2.setX(100);
        e2.setY(100);
        e2.setRadius(10);
        e2.setLayer(LayerTypes.ASTEROID);

        world.addEntity(e1);
        world.addEntity(e2);

        collisionDetector.process(gameData, world);

        // No collision should be detected
        assertTrue(world.getEntities().contains(e1));
        assertTrue(world.getEntities().contains(e2));
    }

}
