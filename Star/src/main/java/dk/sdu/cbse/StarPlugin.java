package dk.sdu.cbse;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.star.Star;
import javafx.scene.paint.Color;

import java.util.Random;


public class StarPlugin implements IGamePluginService {
    private Entity star;


    @Override
    public void start(GameData gameData, World world) {
        for(int i = 0; i < 100; i++) {
            star = createStar();
            world.addEntity(star);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }

    public Entity createStar() {
        Random random = new Random();
        Entity star = new Star();

        star.setPolygonCoordinates(-1, -1, 1, -1, 1,1, -1, 1);
        star.setX(random.nextInt(780));
        star.setY(random.nextInt(780));
        star.setRotation(random.nextInt(181));
        star.setEntityColor(Color.YELLOW);
        star.setLayer(LayerTypes.BACKGROUND);


        return star;
    }
}