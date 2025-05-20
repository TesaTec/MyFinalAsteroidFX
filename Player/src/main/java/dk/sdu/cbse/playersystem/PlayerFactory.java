package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.components.*;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.LayerTypes;
import javafx.scene.paint.Color;

public class PlayerFactory {
    public static Entity createPlayerShip(float x, float y, int size, float rotation, float speed) {
        Entity playerShip = new Player();

        playerShip.addComponent(new TransformComponenet(x, y, rotation, size));
        playerShip.addComponent(new MovementComponent(speed));
        playerShip.addComponent(new GraphicsComponent(LayerTypes.PLAYER, Color.LIGHTBLUE,-15,-10,15,0,-15,10, -5,0));
        playerShip.addComponent(new HealthComponent(3, true));
        playerShip.addComponent(new CollisionComponent(5));
        playerShip.addComponent(new AttackComponent(100, 10, 0));
        playerShip.addComponent(new PlayerComponent());


        return playerShip;
    }

}
