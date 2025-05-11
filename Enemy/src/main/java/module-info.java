import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.*;

module Enemy {
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires CommonEnemy;

    provides IGamePluginService with dk.sdu.cbse.enemycontrol.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.enemycontrol.EnemyControlSystem;
}