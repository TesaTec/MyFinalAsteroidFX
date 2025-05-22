package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.components.AsteroidComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.Random;

public class AsteroidPostProcessing implements IPostEntityProcessingService {
    Random random = new Random();
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            if(!entity.hasComponent(AsteroidComponent.class)) {
                continue;
            }
            HealthComponent healthCP = entity.getComponent(HealthComponent.class);

            if(!healthCP.getAlive()) {
                int pieces = 2;
                if(entity.getComponent(TransformComponenet.class).getSize() >= 8) {
                    if(entity.getComponent(TransformComponenet.class).getSize() >= 12) {
                        pieces = 3;
                    }
                    split(entity, world, pieces);
                }
                world.removeEntity(entity);
            }
        }
    }

    private void split(Entity original, World world, int pieces) {
        TransformComponenet transCP = original.getComponent(TransformComponenet.class);

        for(int i = 0; i < pieces; i++) {
            Entity asteroidPiece = AsteroidFactory.createAsteroid(transCP.getX(), transCP.getY(), transCP.getSize()/2, transCP.getRotation()/2 * pieces, 1.25f, 1);
            world.addEntity(asteroidPiece);
        }
    }
}
