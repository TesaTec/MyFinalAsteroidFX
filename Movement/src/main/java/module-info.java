import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.movement.Movement;

module Movement {
    requires Common;
    requires CommonBullet;

    provides IEntityProcessingService with Movement;

}