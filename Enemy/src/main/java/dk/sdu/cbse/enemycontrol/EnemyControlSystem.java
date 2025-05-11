package dk.sdu.cbse.enemycontrol;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService{

    int attackChance = 90;
    @Override
    public void process(GameData gameData, World world) {
        for(Entity enemy : world.getEntities(Enemy.class)) {

            enemy.setRotation(angleToPoint(enemy, world));

            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));

            enemy.setX(enemy.getX() + changeX * enemy.getSpeed());
            enemy.setY(enemy.getY() + changeY * enemy.getSpeed());


            if(enemy.getX() < 0) {
                enemy.setX(enemy.getX() - gameData.getDisplayWidth());
            }
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(enemy.getX() % gameData.getDisplayWidth());
            }
            if (enemy.getY() < 0 ) {
                enemy.setY(enemy.getY() - gameData.getDisplayHeight());
            }
            if(enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(enemy.getY() % gameData.getDisplayHeight());
            }
            Random rand = new Random();
            if(rand.nextInt(attackChance) == 0) {

                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );

            }

        }
    }

    private double angleToPoint(Entity entity, World world) {
        double deltaX = world.getPlayerCoords()[0] - entity.getX();
        double deltaY = world.getPlayerCoords()[1] - entity.getY();

        return Math.toDegrees(Math.atan2(deltaY, deltaX));
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
