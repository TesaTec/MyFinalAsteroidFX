module ScoringService {
    requires CommonScore;
    requires spring.web;

    provides dk.sdu.cbse.common.scoring.ScoringSPI with dk.sdu.cbse.scoring.ScoringService;
}