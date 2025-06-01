package dk.sdu.cbse.collision;

import dk.sdu.cbse.common.components.CollisionComponent;
import dk.sdu.cbse.common.components.GraphicsComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import org.springframework.stereotype.Component;

@Component
public class CollisionDetector implements IPostEntityProcessingService{
    @Override
    public void process(GameData gameData, World world) {

        for (Entity e : world.getEntities()) {
            CollisionComponent collisionCP = e.getComponent(CollisionComponent.class);
            if (collisionCP != null) {
                collisionCP.setCollided(false);
            }
        }

        for(Entity e1: world.getEntities()) {
            for (Entity e2: world.getEntities()) {

                GraphicsComponent gc1 = e1.getComponent(GraphicsComponent.class);
                GraphicsComponent gc2 = e2.getComponent(GraphicsComponent.class);

                CollisionComponent collisionCP1 =  e1.getComponent(CollisionComponent.class);
                CollisionComponent collisionCP2 =  e2.getComponent(CollisionComponent.class);

                if(gc1 == null || gc2 == null || collisionCP1 == null || collisionCP2 == null) {
                    continue;
                }

                //Ensures that it doesn't collide with types of itself
                if(gc1.getLayer() == gc2.getLayer()){
                    continue;
                }

                //Ensures that the stars don't collide with anything
                if(gc1.getLayer() == LayerTypes.BACKGROUND || gc2.getLayer() == LayerTypes.BACKGROUND) {
                    continue;
                }

                // Ensures that Asteroids and enemies can't collide
                if(gc1.getLayer() == LayerTypes.ASTEROID && gc2.getLayer() == LayerTypes.ENEMY ||gc1.getLayer() == LayerTypes.ENEMY && gc2.getLayer() == LayerTypes.ASTEROID) {
                    continue;
                }

                if (this.collides(e1, e2)) {
                    collisionCP1.setCollided(true);
                    collisionCP2.setCollided(true);
                }


            }
        }
    }

    public Boolean collides(Entity e1, Entity other) {
        TransformComponenet trans1CP = e1.getComponent(TransformComponenet.class);
        TransformComponenet trans2CP = other.getComponent(TransformComponenet.class);

        CollisionComponent collisionCP1 =  e1.getComponent(CollisionComponent.class);
        CollisionComponent collisionCP2 =  other.getComponent(CollisionComponent.class);

        if(trans1CP == null || trans2CP == null) {
            return false;
        }
        float deltaX = (trans1CP.getX() - trans2CP.getX());
        float deltaY =(trans1CP.getY() - trans2CP.getY());

        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < (collisionCP1.getRadius() + collisionCP2.getRadius());
    }
}
