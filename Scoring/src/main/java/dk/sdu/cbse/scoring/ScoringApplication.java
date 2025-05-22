package dk.sdu.cbse.scoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
public class ScoringApplication {

	private int totalScore = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringApplication.class, args);
	}

	@PostMapping("/score")
	public int addScore(@RequestParam("score") int score) {
		return totalScore += score;
	}

	@GetMapping("/getScore")
	public int getTotalScore() {
		return totalScore;
	}

}
