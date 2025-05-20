import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires spring.context;

    exports dk.sdu.cbse.bullet to spring.beans, spring.context;
    provides IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.bullet.BulletControlSystem;
    provides BulletSPI with dk.sdu.cbse.bullet.BulletFactory;
}