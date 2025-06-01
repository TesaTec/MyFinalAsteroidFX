package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Called when the systems start and is responisble for initializing the plugins
     * @param gameData The object containing the current game state
     * @param world The world containing all entities in the game
     * @pre gameData and world are not null. Plugins are not started
     * @post Plugins are now started.
     */

    void start(GameData gameData, World world);
    /**
     * Called when the system stops. Ensures the plugin is stopped and cleaned up.
     *
     * @param gameData The object containing the current game state.
     * @param world The world containing all entities in the game.
     * @pre gameData and world are not null. Plugins are started.
     * @post Plugin is stopped and its entities are removed from the world.
     */
    void stop(GameData gameData, World world);
}
