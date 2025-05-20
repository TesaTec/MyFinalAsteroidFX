package dk.sdu.cbse;

import dk.sdu.cbse.common.components.BackgroundComponent;
import dk.sdu.cbse.common.components.GraphicsComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.LayerTypes;
import dk.sdu.cbse.common.star.Star;
import javafx.scene.paint.Color;

import java.util.Random;

public class StarFactory {
    public static Entity createStar(float x, float y, int size, float rotation) {
        Entity star = new Star();

        star.addComponent(new TransformComponenet(x, y, rotation, size));
        star.addComponent(new GraphicsComponent(LayerTypes.BACKGROUND, Color.YELLOW, -size, -size, size, -size, size, size, -size, size));

        star.addComponent(new BackgroundComponent());

        return star;
    }
}
