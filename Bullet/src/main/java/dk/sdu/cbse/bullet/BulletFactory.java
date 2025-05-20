package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.components.*;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import javafx.scene.paint.Color;
import dk.sdu.cbse.common.bullet.BulletSPI;


public class BulletFactory implements BulletSPI {


    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();

        TransformComponenet transCP = shooter.getComponent(TransformComponenet.class);

        double changeX = Math.cos(Math.toRadians(transCP.getRotation()));
        double changeY = Math.sin(Math.toRadians(transCP.getRotation()));

        bullet.addComponent(new TransformComponenet((float)(transCP.getX() + changeX * 10), (float)(transCP.getY() + changeY * 10), transCP.getRotation(), 2));
        bullet.addComponent(new MovementComponent(4));
        bullet.addComponent(new GraphicsComponent(LayerTypes.BULLET, Color.GREY,-2,-2, -2,-2, 2,-2, 2,2 ));
        bullet.addComponent(new HealthComponent(1, true));
        bullet.addComponent(new CollisionComponent(2));
        bullet.addComponent(new BulletComponent());

        return bullet;
    }
}
