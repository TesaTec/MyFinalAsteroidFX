import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    requires javafx.graphics;
    requires spring.context;

    exports dk.sdu.cbse.asteroidControl to spring.beans, spring.context;
    provides IGamePluginService with dk.sdu.cbse.asteroidControl.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroidControl.AsteroidWrapAround;
    provides IPostEntityProcessingService with dk.sdu.cbse.asteroidControl.AsteroidPostProcessing;

}