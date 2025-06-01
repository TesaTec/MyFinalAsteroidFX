package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Called during the game loop to process entities
     *
     * @param gameData The object for contraining the current game state.
     * @param world The world containing all entites in the game.
     * @pre gameData and world must not be null
     * @post All entities in the world are processed.
     */
    void process(GameData gameData, World world);
}
