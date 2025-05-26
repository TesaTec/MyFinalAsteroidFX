import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Star {
    requires Common;
    requires CommonStar;
    requires spring.context;

    exports dk.sdu.cbse to spring.beans, spring.context;

    provides IGamePluginService with dk.sdu.cbse.StarPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.StarControlSystem;
}