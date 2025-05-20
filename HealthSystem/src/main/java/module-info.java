import dk.sdu.cbse.common.services.IPostEntityProcessingService;
module HealthSystem {
    requires Common;
    requires spring.context;
    exports dk.sdu.cbse.health to spring.beans, spring.context;
    provides IPostEntityProcessingService with dk.sdu.cbse.health.HealthSystem;
}