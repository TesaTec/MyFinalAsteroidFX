package dk.sdu.cbse.movement;

import dk.sdu.cbse.common.components.MovementComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import org.springframework.stereotype.Component;

@Component
public class Movement implements IEntityProcessingService {

    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities()) {

            TransformComponenet trans = entity.getComponent(TransformComponenet.class);
            MovementComponent movement = entity.getComponent(MovementComponent.class);

            if(trans == null || movement == null) {
                continue;
            }

            float radians = (float) Math.toRadians(trans.getRotation());

            System.out.println(movement.getSpeed());

            float deltaX = (float) Math.cos(radians) * movement.getSpeed();
            float deltaY = (float) Math.sin(radians) * movement.getSpeed();

            trans.setX(trans.getX() + deltaX);
            trans.setY(trans.getY() + deltaY);


        }
    }


}
