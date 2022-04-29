package pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends GamePiece {

    public Queen(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {

        final Rook dummyRook = new Rook(this.getColor(), this.getX(), this.getY(), this.picture);
        final Bishop dummyBishop = new Bishop(this.getColor(), this.getX(), this.getY(), this.picture);

        ArrayList<Point> validMoves = new ArrayList();
        validMoves.addAll(List.of(dummyRook.validMoves()));
        validMoves.addAll(List.of(dummyBishop.validMoves()));

        Point[] returnArray = validMoves.toArray(new Point[0]);

        return returnArray;
    }
}