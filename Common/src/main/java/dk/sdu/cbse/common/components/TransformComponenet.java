package dk.sdu.cbse.common.components;

public class TransformComponenet {
    private float x, y, rotation;
    private int size;

    public TransformComponenet(float x, float y, float rotation, int size) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.size = size;
    }


    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
