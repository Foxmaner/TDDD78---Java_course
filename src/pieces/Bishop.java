package pieces;

import com.company.GamePiece;

import java.awt.*;


public class Bishop extends GamePiece {

    public Bishop(Color c, int x, int y) {
        super(c, new Point(x, y));
        if (this.color == Color.WHITE) {
            fileName = "wB.png";
        } else {
            fileName = "bB.png";
        }

    }

    @Override
    public Point[] validMoves() {


        Point[] validMoves = new Point[8];
        int i = 0;
        int k = 1;
        //one diagonal
        for (int x = 0; x <= 8; x++) {
            for (int y = 0; y <= 8; y++) {
                validMoves[i] = new Point(x, y);
                i++;
            }
        }


        return validMoves;
    }
}
