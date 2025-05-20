package dk.sdu.cbse.common.components;

public class CollisionComponent {
    private boolean hasCollided;

    private int radius;

    public CollisionComponent(int radius) {
        this.radius = radius;
        this.hasCollided = false;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isCollided() {
        return hasCollided;
    }

    public void setCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }
}
