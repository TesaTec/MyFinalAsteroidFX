package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity bullet: world.getEntities(Bullet.class)) {

            TransformComponenet transCP = bullet.getComponent(TransformComponenet.class);

            if(transCP.getX() < 0 || transCP.getX() > gameData.getDisplayWidth()) {
                world.removeEntity(bullet);
            }
            if(transCP.getY() < 0 || transCP.getY() > gameData.getDisplayHeight()) {
                world.removeEntity(bullet);
            }
        }
    }

}
