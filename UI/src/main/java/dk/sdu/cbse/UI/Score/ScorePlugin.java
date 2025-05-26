package dk.sdu.cbse.UI.Score;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IHUDPluginService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import dk.sdu.cbse.common.scoring.ScoringSPI;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


@Component
public class ScorePlugin implements IHUDPluginService {

    private Text scoreText = new Text();
    @Override
    public void setupHUD(Pane window, GameData gameData) {
        scoreText.setX(20);
        scoreText.setY(20);
        scoreText.setStyle("-fx-font: 18 arial;");
        scoreText.setFill(Color.WHITE);
        scoreText.setText("Score: 0");
        window.getChildren().add(scoreText);

    }

    @Override
    public void updateHUD(GameData gameData) {
        int score = getScoreSPI().stream()
                .findFirst()
                .map(ScoringSPI::getScore)
                .orElse(0);
       scoreText.setText("Score: " + score);
        System.out.println("This is the score from the server: " + score);
    }

    private Collection<? extends ScoringSPI> getScoreSPI() {
        return ServiceLoader.load(ScoringSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
