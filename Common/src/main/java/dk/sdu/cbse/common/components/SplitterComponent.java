package dk.sdu.cbse.common.components;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;

import java.util.function.Supplier;

public class SplitterComponent {

    public int pieces;

    public SplitterComponent(int pieces) {
        this.pieces = pieces;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }
}
