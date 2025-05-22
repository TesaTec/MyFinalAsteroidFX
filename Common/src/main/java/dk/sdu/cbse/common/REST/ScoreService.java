package dk.sdu.cbse.common.REST;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreService {
    private HttpClient client;
    private String urlToScore;

    public ScoreService(String scoreRetrieverURL) {
        this.client = HttpClient.newHttpClient();
        this.urlToScore = scoreRetrieverURL;
    }

    public int getScore() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlToScore + "/getScore")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200) {
            return Integer.parseInt(response.body());
        } else {
            throw new RuntimeException("Failed to retrieve the score: " + response.statusCode());
        }
    }

    public void addScore(int score) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlToScore + "/score?score=" + score)).POST(HttpRequest.BodyPublishers.noBody()).build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException |InterruptedException e) {
            System.out.println("Failed to update the score " + e.getMessage()) ;
        }
    }
}
