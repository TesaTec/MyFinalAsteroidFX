import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Enemy {
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    requires Common;
    requires CommonBullet;

    requires CommonEnemy;
    requires javafx.graphics;
    requires spring.context;

    exports dk.sdu.cbse.enemycontrol to spring.beans, spring.context;

    provides IGamePluginService with dk.sdu.cbse.enemycontrol.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemycontrol.EnemyMovement;
}