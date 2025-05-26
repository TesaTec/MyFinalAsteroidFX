package dk.sdu.cbse.UI.Score;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.score.ScoreSPI;
import dk.sdu.cbse.common.services.IHUDPluginService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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

            int score = getScoreSPIs().stream().findFirst().map(ScoreSPI::getScore).orElse(0);
            scoreText.setText("Score: " + score);
            System.out.println("SCORE FROM SERVER: " + score);
    }

    private Collection<? extends ScoreSPI> getScoreSPIs() {
        return  ServiceLoader.load(ScoreSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
