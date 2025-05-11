package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService{
    @Override
    public void process(GameData gameData, World world) {
        for(Entity e1: world.getEntities()) {
            for (Entity e2: world.getEntities()) {

                //Ensures that it doesn't collide with types of itself
                if(e1.getLayer() == e2.getLayer()){
                    continue;
                }

                //Ensures that the stars don't collide with anything
                if(e1.getLayer() == LayerTypes.BACKGROUND || e2.getLayer() == LayerTypes.BACKGROUND) {
                    continue;
                }

                // Ensures that Asteroids and enemies can't collide
                if(e1.getLayer() == LayerTypes.ASTEROID && e2.getLayer() == LayerTypes.ENEMY ||e1.getLayer() == LayerTypes.ENEMY && e2.getLayer() == LayerTypes.ASTEROID) {
                    continue;
                }

                if(this.collides(e1, e2)) {

                    if(e1.getHealth() < 0 ) {
                        world.removeEntity(e1);

                    } else {
                        e1.setHealth(e1.getHealth()-1);
                    }
                    if(e2.getHealth() < 0 ) {
                        world.removeEntity(e2);
                    } else {
                        e2.setHealth(e2.getHealth()-1);
                    }
                }
            }
        }
    }

    public Boolean collides(Entity e1, Entity other) {
        float deltaX = (float) (e1.getX() - other.getX());
        float deltaY = (float) (e1.getY() - other.getY());

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < (e1.getRadius() + other.getRadius());
    }
}
