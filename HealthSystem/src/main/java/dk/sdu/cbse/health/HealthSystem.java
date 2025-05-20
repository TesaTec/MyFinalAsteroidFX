package dk.sdu.cbse.health;

import dk.sdu.cbse.common.components.CollisionComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class HealthSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> toRemove = new ArrayList<>();

        for (Entity entity : world.getEntities()) {
            CollisionComponent collisionCP = entity.getComponent(CollisionComponent.class);
            HealthComponent healthCP = entity.getComponent(HealthComponent.class);

            if (healthCP == null || collisionCP == null) {
                continue;
            }

            if (collisionCP.isCollided()) {
                healthCP.setHealth(healthCP.getHealth() - 1);
            }

            if (healthCP.getHealth() <= 0) {
                toRemove.add(entity);
            }
        }

        for (Entity e : toRemove) {
            world.removeEntity(e);
        }
    }

}
