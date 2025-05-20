import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    exports dk.sdu.cbse.collision to spring.beans, spring.context;
    provides IPostEntityProcessingService with dk.sdu.cbse.collision.CollisionDetector;
}