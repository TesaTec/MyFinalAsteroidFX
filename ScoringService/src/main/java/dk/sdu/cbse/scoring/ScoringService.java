package dk.sdu.cbse.scoring;

import dk.sdu.cbse.common.scoring.ScoringSPI;
import org.springframework.web.client.RestTemplate;


public class ScoringService implements ScoringSPI {

    private RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080";
    @Override
    public int getScore() {
        String score = "0";
        try {
             score = restTemplate.getForObject(url + "/getScore", String.class);
            assert score != null;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(score);
    }

    @Override
    public void setScore(int score) {
        try {
            restTemplate.postForObject(url + "/score?score=" + score, null, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}