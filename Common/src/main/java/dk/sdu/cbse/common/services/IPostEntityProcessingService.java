package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {
    /**
     * Called after the main entity proccessing. Used for additional logic
     * @param gameData the object for containing the current game state
     * @param world the world containing all the entities in the game
     * @pre gameData and world must not be null. All entities have been processed
     * @post Additional processing on the entitites are completed.
     */

    void process(GameData gameData, World world);
}
