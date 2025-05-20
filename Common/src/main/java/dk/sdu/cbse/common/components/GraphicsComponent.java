package dk.sdu.cbse.common.components;

import dk.sdu.cbse.common.data.LayerTypes;
import javafx.scene.paint.Color;

public class GraphicsComponent {


    private LayerTypes layer = LayerTypes.BACKGROUND; // Default Layer

    private Color entityColor;
    private double[] polygonCoordinates;

    public GraphicsComponent(LayerTypes layer, Color entityColor, double... polygonCoordinates) {
        this.layer = layer;
        this.entityColor = entityColor;
        this.polygonCoordinates = polygonCoordinates;
    }

    public void setLayer(LayerTypes layer) {this.layer = layer;}

    public LayerTypes getLayer() {return layer;}

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setEntityColor(Color color) {this.entityColor = color;}
    public Color getEntityColor() {return entityColor;}

}
