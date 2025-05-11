import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.cbse.playersystem;
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerControlSystem;
}