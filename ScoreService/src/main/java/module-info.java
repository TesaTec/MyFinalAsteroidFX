module ScoreService {
    requires CommonScore;
    requires spring.web;

    provides dk.sdu.cbse.common.score.ScoreSPI with dk.sdu.cbse.scoring.ScoreService;
}