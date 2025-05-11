package dk.sdu.cbse.common.data;

public enum LayerTypes {
    BACKGROUND(0),
    ASTEROID(1),
    ENEMY(2),
    PLAYER(3),
    BULLET(4),
    UI(5);

    private final int value;

    LayerTypes(int value) {
        this.value = value;
    }

    public int getValue() {return value;}
}
