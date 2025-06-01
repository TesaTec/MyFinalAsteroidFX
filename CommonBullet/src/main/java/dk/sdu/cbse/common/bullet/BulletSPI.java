package dk.sdu.cbse.common.bullet;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public interface BulletSPI {
    /**
     *  Called when a bullet needs to be created by an entity (e.g. player)
     * @param entity The entity that is firing the bullet
     * @param gameData The object containing the current game state
     * @return A bullet
     * @pre entity and gameData is not null
     * @post A bullet is created and returned
     */
    Entity createBullet(Entity entity, GameData gameData);
}
