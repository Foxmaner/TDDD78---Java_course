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


        Point[] validMoves = new Point[7*4];
        int i = 0;
        //down right
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y + k);
            i++;
        }
        //down left
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y + k);
            i++;
        }
        //up right
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x + k, pos.y - k);
            i++;
        }
        //up left
        for (int k = 1; k <= 7; k++) {
            validMoves[i] = new Point(pos.x - k, pos.y - k);
            i++;
        }

        return validMoves;
    }
}
