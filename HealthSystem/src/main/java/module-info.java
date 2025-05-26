import dk.sdu.cbse.common.services.IPostEntityProcessingService;
module HealthSystem {
    requires Common;
    requires spring.context;
    requires CommonScore;
    exports dk.sdu.cbse.health to spring.beans, spring.context;
    provides IPostEntityProcessingService with dk.sdu.cbse.health.HealthSystem;
    uses dk.sdu.cbse.common.scoring.ScoringSPI;
}