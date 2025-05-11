package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

public class BulletPlugin implements IGamePluginService, BulletSPI {

    private Entity bullet;
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity: world.getEntities()) {
            if(entity.getClass() == Bullet.class) {
                world.removeEntity(entity);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(-2,-2, -2,-2, 2,-2, 2,2 );

        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));

        bullet.setX(shooter.getX() + changeX * 10);
        bullet.setY(shooter.getY() + changeY * 10);

        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(2);
        bullet.setEntityColor(Color.GREY);
        bullet.setLayer(LayerTypes.BULLET);
        bullet.setHealth(1);

        return bullet;
    }
}
