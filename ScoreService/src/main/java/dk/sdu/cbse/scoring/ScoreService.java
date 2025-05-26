package dk.sdu.cbse.scoring;

import dk.sdu.cbse.common.score.ScoreSPI;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class ScoreService implements ScoreSPI {
    private RestTemplate rest;
    private String urlToScore;


    public ScoreService() {
        this.rest = new RestTemplate();
        this.urlToScore = "http://localhost:8080";
    }



    @Override
    public int getScore() {
        try {
            String response = rest.getForObject(urlToScore + "/getScore", String.class);
            return Integer.parseInt(response);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Could not retrieve score " + e.getStatusCode());
        }

    }

    @Override
    public void addScore(int score) {
        try {
            rest.postForLocation(urlToScore + "/score?score=" + score, null);
        } catch (Exception e) {
            System.out.println("Failed to update the score " + e.getMessage()) ;
        }
    }
}
