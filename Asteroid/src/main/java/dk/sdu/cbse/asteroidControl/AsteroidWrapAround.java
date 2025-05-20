package dk.sdu.cbse.asteroidControl;

import dk.sdu.cbse.common.Asteroid;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidWrapAround implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroid : world.getEntities(Asteroid.class)) {
            TransformComponenet transCP = asteroid.getComponent(TransformComponenet.class);
            if(transCP.getX() < 0) {
                transCP.setX(transCP.getX() - gameData.getDisplayWidth());
            }
            if (transCP.getX() > gameData.getDisplayWidth()) {
                transCP.setX(transCP.getX() % gameData.getDisplayWidth());
            }
            if (transCP.getY() < 0 ) {
                transCP.setY(transCP.getY() - gameData.getDisplayHeight());
            }
            if(transCP.getY() > gameData.getDisplayHeight()) {
                transCP.setY(transCP.getY() % gameData.getDisplayHeight());
            }
        }
    }
}
