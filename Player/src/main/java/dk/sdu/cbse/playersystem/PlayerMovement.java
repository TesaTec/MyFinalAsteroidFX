package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.components.AttackComponent;
import dk.sdu.cbse.common.components.MovementComponent;
import dk.sdu.cbse.common.components.PlayerComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Component
public class PlayerMovement implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for(Entity player: world.getEntities()) {
            if(!player.hasComponent(PlayerComponent.class)) {
                continue;
            }

            TransformComponenet transCP = player.getComponent(TransformComponenet.class);
            MovementComponent moveCP = player.getComponent(MovementComponent.class);
            AttackComponent attackCP = player.getComponent(AttackComponent.class);

            if(gameData.getKeys().isDown(GameKeys.LEFT)) {
                transCP.setRotation(transCP.getRotation() -5);
            }

            if(gameData.getKeys().isDown(GameKeys.RIGHT)) {
                transCP.setRotation(transCP.getRotation() +5);
            }

            if(gameData.getKeys().isDown(GameKeys.UP)) {
                moveCP.setSpeed(3);
            } else {
                moveCP.setSpeed(0);
            }

            attackCP.setTimeSinceLastAttacked(attackCP.getTimeSinceLastAttacked()+1);
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {



                if(attackCP.getTimeSinceLastAttacked() >= attackCP.getCoolDown()) {
                    attackCP.setTimeSinceLastAttacked(0);
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> world.addEntity(spi.createBullet(player, gameData))
                    );
                }

            }
            if (transCP.getX() < 0) {
                transCP.setX(1);
            }

            if (transCP.getX() > gameData.getDisplayWidth()) {
                transCP.setX(gameData.getDisplayWidth()-1);
            }

            if (transCP.getY() < 0) {
                transCP.setY(1);
            }

            if (transCP.getY() > gameData.getDisplayHeight()) {
                transCP.setY(gameData.getDisplayHeight()-1);
            }

            world.setPlayerCoords(transCP.getX(), transCP.getY());
        }

    }


    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


}
