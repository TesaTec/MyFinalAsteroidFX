package dk.sdu.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;
import javafx.scene.paint.Color;


public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;

    private double speed;

    private LayerTypes layer = LayerTypes.BACKGROUND; // Default Layer

    private Color entityColor;

    private int health;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
        
    public float getRadius() {
        return this.radius;
    }

    public void setSpeed(double speed) {this.speed = speed;}

    public double getSpeed() {return speed;}

    public void setEntityColor(Color color) {this.entityColor = color;}
    public Color getEntityColor() {return entityColor;}

    public void setLayer(LayerTypes layer) {this.layer = layer;}

    public LayerTypes getLayer() {return layer;}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
