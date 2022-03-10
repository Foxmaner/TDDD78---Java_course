package pieces;

import com.company.GamePiece;

import java.awt.*;

public class Rook extends GamePiece {

    public Rook(Color c, int x, int y, Image picture) {
        super(c, new Point(x, y), picture);
    }

    @Override
    public Point[] validMoves() {

        Point[] validMoves = new Point[8*2];
        int i = 0;
        // x axle
        for (int x = 0; x <= 8; x++) {
            if (x == pos.x) {
                continue;
            }
            validMoves[i] = new Point(x, pos.y);
            i++;
        }
        // y axle
        for (int y = 0; y <= 8; y++) {
            if (y == pos.y) {
                continue;
            }
            validMoves[i] = new Point(pos.x, y);
            i++;
        }

        return validMoves;
    }
}
