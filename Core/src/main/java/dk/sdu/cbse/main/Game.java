package dk.sdu.cbse.main;

import dk.sdu.cbse.common.components.GraphicsComponent;
import dk.sdu.cbse.common.components.TransformComponenet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IHUDPluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;


@Component
public class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final Pane uiWindow = new Pane();
    private final StackPane root = new StackPane();

    @Autowired
    private  List<IGamePluginService> gamePluginServices;

    @Autowired
    private  List<IEntityProcessingService> entityProcessingServices;

    @Autowired
    private  List<IPostEntityProcessingService> postEntityProcessingServices;

    @Autowired
    private  List<IHUDPluginService> hudPluginServices;

    public Game() {
    }


    public void start(Stage window) throws Exception {

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        root.getChildren().addAll(gameWindow, uiWindow);
        Scene scene = new Scene(root);
        setupInput(scene);


        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }

        for(IHUDPluginService iHudPlugin: hudPluginServices) {
            iHudPlugin.setupHUD(uiWindow, gameData);
        }
        for (Entity entity : world.getEntities()) {
            GraphicsComponent gc = entity.getComponent(GraphicsComponent.class);
            Polygon polygon = new Polygon(gc.getPolygonCoordinates());

            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        scene.setFill(Color.BLACK);
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : entityProcessingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }

        for( IHUDPluginService hudPluginService : hudPluginServices) {
            hudPluginService.updateHUD(gameData);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {


            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        gameWindow.getChildren().clear();

        polygons.clear();

        List<Entity> entities = new ArrayList<>(world.getEntities());

        entities.sort(Comparator.comparingInt(entity -> {
            var graphics = entity.getComponent(GraphicsComponent.class);
            return graphics != null ? graphics.getLayer().getValue() : Integer.MAX_VALUE;
        }));

        for (Entity entity : entities) {
            TransformComponenet transCP = entity.getComponent(TransformComponenet.class);
            GraphicsComponent graphicsCP = entity.getComponent(GraphicsComponent.class);
            if(transCP == null || graphicsCP == null) {
                continue;
            }
            Polygon polygon = new Polygon(graphicsCP.getPolygonCoordinates());
            polygon.setTranslateX(transCP.getX());
            polygon.setTranslateY(transCP.getY());
            polygon.setRotate(transCP.getRotation());
            polygon.setFill(graphicsCP.getEntityColor());

            polygons.put(entity, polygon);

            gameWindow.getChildren().add(polygon);


        }


    }

    private void setupInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT) || event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT) || event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT) || event.getCode().equals(KeyCode.A)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT) || event.getCode().equals(KeyCode.D)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.W)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });
    }

}
