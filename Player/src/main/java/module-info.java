import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Player {
    exports dk.sdu.cbse.playersystem;
    requires Common;
    requires CommonBullet;
    requires spring.context;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerMovement;
    provides IPostEntityProcessingService with dk.sdu.cbse.playersystem.PlayerPostProcessing;
}