import dk.sdu.cbse.common.services.IPostEntityProcessingService;
module HealthSystem {
    requires Common;
    provides IPostEntityProcessingService with dk.sdu.cbse.health.HealthSystem;
}