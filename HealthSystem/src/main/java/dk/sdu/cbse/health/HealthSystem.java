package dk.sdu.cbse.health;

import dk.sdu.cbse.common.components.AsteroidComponent;
import dk.sdu.cbse.common.components.CollisionComponent;
import dk.sdu.cbse.common.components.EnemyComponent;
import dk.sdu.cbse.common.components.HealthComponent;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.score.ScoreSPI;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Component
public class HealthSystem implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
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
                if(entity.hasComponent(EnemyComponent.class) || entity.hasComponent(AsteroidComponent.class)) {
                    getScoreSPIs().stream().findFirst().ifPresent(spi -> {
                        spi.addScore(1);
                    });
                }
                    healthCP.setAlive(false);
            }
        }

    }

    private Collection<? extends ScoreSPI> getScoreSPIs() {
        return  ServiceLoader.load(ScoreSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
