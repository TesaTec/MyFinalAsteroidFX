import dk.sdu.cbse.common.services.IPostEntityProcessingService;
module HealthSystem {
    requires Common;
    requires spring.context;
    requires CommonScore;

    uses dk.sdu.cbse.common.score.ScoreSPI;
    exports dk.sdu.cbse.health to spring.beans, spring.context;
    provides IPostEntityProcessingService with dk.sdu.cbse.health.HealthSystem;
}