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
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-15,-10,15,0,-15,10, -5,0);
        playerShip.setRadius(10);
        playerShip.setX(gameData.getDisplayWidth()/2);
        playerShip.setY(gameData.getDisplayHeight()/2);
        playerShip.setEntityColor(Color.LIGHTBLUE);
        playerShip.setLayer(LayerTypes.PLAYER);
        playerShip.setHealth(4);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
