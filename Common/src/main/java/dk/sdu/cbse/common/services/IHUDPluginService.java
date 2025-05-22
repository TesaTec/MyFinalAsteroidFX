package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import javafx.scene.layout.Pane;

public interface IHUDPluginService {
    void setupHUD(Pane window, GameData gameData);

    void updateHUD(GameData gameData);
}
