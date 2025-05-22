package dk.sdu.cbse.UI.Score;

import dk.sdu.cbse.common.REST.ScoreService;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.services.IHUDPluginService;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ScorePlugin implements IHUDPluginService {

    private Text scoreText = new Text();
    private ScoreService scoreService;
    @Override
    public void setupHUD(Pane window, GameData gameData) {
         scoreService = new ScoreService("http://localhost:8080");
        scoreText.setX(20);
        scoreText.setY(20);
        scoreText.setStyle("-fx-font: 18 arial;");
        scoreText.setFill(Color.WHITE);
        scoreText.setText("Score: 0");
        window.getChildren().add(scoreText);

    }

    @Override
    public void updateHUD(GameData gameData) {
        if(scoreService == null) {
            scoreService = new ScoreService("http://localhost:8080");
        }
        try {
            int score = scoreService.getScore();
            scoreText.setText("Score: " + score);
            System.out.println("SCORE FROM SERVER: " + score);


        } catch (IOException | InterruptedException e) {
            System.out.println("an error occurred during the receiving of the score");
        }
    }
}
