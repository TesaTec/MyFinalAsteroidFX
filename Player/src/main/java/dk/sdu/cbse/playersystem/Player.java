package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
public class Player extends Entity{
    private int timeSinceLastAttacked = 5;

    public int getTimeSinceLastAttacked() {
        return timeSinceLastAttacked;
    }

    public void setTimeSinceLastAttacked(int timeSinceLastAttacked) {
        this.timeSinceLastAttacked = timeSinceLastAttacked;
    }
}
