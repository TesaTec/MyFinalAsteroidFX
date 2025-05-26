package dk.sdu.cbse.scoring;

import dk.sdu.cbse.common.scoring.ScoringSPI;
import org.springframework.web.client.RestTemplate;


public class ScoringService implements ScoringSPI {

    private RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080";
    @Override
    public int getScore() {
        String score = restTemplate.getForObject(url + "/getScore", String.class);
        assert score != null;
        return Integer.parseInt(score);
    }

    @Override
    public void setScore(int score) {
        restTemplate.postForObject(url + "/score?score=" + score, null, String.class);
    }
}