package dk.sdu.cbse.common.components;

public class MovementComponent {

    private float speed;
    private float angleInDegrees;

    public MovementComponent(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
