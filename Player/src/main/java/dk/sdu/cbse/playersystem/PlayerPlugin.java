package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;

public class PlayerPlugin implements IGamePluginService{

    private Entity player;
    @Override
    public void start(GameData gameData, World world) {
        player = PlayerFactory.createPlayerShip(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2, 10, 90, 3);

        world.addEntity(player);
    }


    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
