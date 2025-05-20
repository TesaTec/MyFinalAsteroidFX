package dk.sdu.cbse;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class StarPlugin implements IGamePluginService {
    Random random = new Random();



    @Override
    public void start(GameData gameData, World world) {
        for(int i = 0; i < 100; i++) {
            world.addEntity(StarFactory.createStar(random.nextInt(780), random.nextInt(780), 1 ,random.nextInt(181) ));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }


}