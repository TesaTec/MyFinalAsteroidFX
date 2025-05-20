package dk.sdu.cbse.common.components;

public class HealthComponent {

    private int health;

    private boolean isAlive;
    public HealthComponent( int health, boolean isAlive) {
        this.health = health;
        this.isAlive = isAlive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean status) {
        this.isAlive = status;
    }
}
