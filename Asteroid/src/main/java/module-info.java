import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    requires javafx.graphics;

    provides IGamePluginService with dk.sdu.cbse.asteroidControl.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.cbse.asteroidControl.AsteroidControlSystem;
}