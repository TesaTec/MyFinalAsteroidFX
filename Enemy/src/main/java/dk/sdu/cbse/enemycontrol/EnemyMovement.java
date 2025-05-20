package dk.sdu.cbse.enemycontrol;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.components.AttackComponent;
import dk.sdu.cbse.common.components.EnemyComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyMovement implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity enemy : world.getEntities()) {

            if(!enemy.hasComponent(EnemyComponent.class)) {
                continue;
            }

            TransformComponenet transCP = enemy.getComponent(TransformComponenet.class);
            AttackComponent attackCP = enemy.getComponent(AttackComponent.class);

            transCP.setRotation((float) angleToPoint(transCP, world));


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

            Random rand = new Random();

            if(rand.nextInt(attackCP.getAttackChance()) == 0) {

                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );

            }



        }
    }

    private double angleToPoint(TransformComponenet transCP, World world) {

        double deltaX = world.getPlayerCoords()[0] - transCP.getX();
        double deltaY = world.getPlayerCoords()[1] - transCP.getY();

        return Math.toDegrees(Math.atan2(deltaY, deltaX));
    }


    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
