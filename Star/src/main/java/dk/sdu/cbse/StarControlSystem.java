package dk.sdu.cbse;

import dk.sdu.cbse.common.components.GraphicsComponent;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import dk.sdu.cbse.common.star.Star;
import javafx.scene.paint.Color;

import java.util.Random;

public class StarControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity star : world.getEntities(Star.class)) {
            Random random = new Random();
            GraphicsComponent gc = star.getComponent(GraphicsComponent.class);

            int color = random.nextInt(3);

            if (color == 1) {
                gc.setEntityColor(Color.YELLOW);
            } else {
                gc.setEntityColor(Color.LIGHTYELLOW);
            }
        }
    }
}
