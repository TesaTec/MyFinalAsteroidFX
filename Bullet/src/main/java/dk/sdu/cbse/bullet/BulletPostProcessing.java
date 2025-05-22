package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.components.AsteroidComponent;
import dk.sdu.cbse.common.components.BulletComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class BulletPostProcessing implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            if(!entity.hasComponent(BulletComponent.class)) {
                continue;
            }
            HealthComponent healthCP = entity.getComponent(HealthComponent.class);

            if(!healthCP.getAlive()) {
                world.removeEntity(entity);
            }
        }
    }
}
