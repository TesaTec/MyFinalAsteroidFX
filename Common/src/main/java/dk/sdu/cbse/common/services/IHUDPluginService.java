package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import javafx.scene.layout.Pane;

public interface IHUDPluginService {
    /**
     * Called when the system starts. Loads and initialized all the HUD elements
     * @param window The pane on which the HUD should be displayed on
     * @param gameData The object containing the current game state
     * @pre gameData and pane must not be null. HUDPlugins have not already been initialzed
     * @post HUDPlugins are initialized and attached to the window
     */
    void setupHUD(Pane window, GameData gameData);

    /**
     * Called to update the HUD
     * @param gameData The object containing the current game state
     * @pre gameData is not null
     * @post HUD is updated
     */
    void updateHUD(GameData gameData);
}
