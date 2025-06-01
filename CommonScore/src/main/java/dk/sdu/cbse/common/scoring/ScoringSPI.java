package dk.sdu.cbse.common.scoring;

public interface ScoringSPI {
     /**
      * Retrieves the current score
      * @return The current score
      * @pre The score storage is not null.
      * @post the total score is returned
      */
     int getScore();

     /**
      * Adds a value to the current score
      * @param score The value to add to the total score
      * @pre the score storage is not null
      * @post the score is incremented by the score value
      */

     void setScore(int score);
}
