import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.movement.Movement;

module Movement {
    requires Common;
    requires CommonBullet;
    requires spring.context;
    exports dk.sdu.cbse.movement to spring.beans, spring.context;

    provides IEntityProcessingService with Movement;

}