package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.components.AsteroidComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.components.SplitterComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class AsteroidPostProcessing implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            if(!entity.hasComponent(AsteroidComponent.class)) {
                continue;
            }
            HealthComponent healthCP = entity.getComponent(HealthComponent.class);

            if(!healthCP.getAlive()) {
                if(entity.hasComponent(SplitterComponent.class)) {
                    split(entity, world);
                }
                world.removeEntity(entity);
            }
        }
    }

    private void split(Entity original, World world) {
        TransformComponenet transCP = original.getComponent(TransformComponenet.class);
        SplitterComponent splitCP = original.getComponent(SplitterComponent.class);

        for(int i = 0; i < splitCP.getPieces(); i++) {
            Entity asteroidPiece = AsteroidFactory.createAsteroid(
                    transCP.getX(),
                    transCP.getY(),
                    transCP.getSize()/2,
                    transCP.getRotation() * 180 + (360f / splitCP.getPieces()) * i,
                    1.25f,
                    1,
                    0);
            world.addEntity(asteroidPiece);
        }
    }
}
