package pieces;

import com.company.Board;
import com.company.GamePiece;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends GamePiece {

    @Override
    public Point[] validMoves() {
        int direction = 0;
        if (this.getColor() == Color.WHITE) {
            direction = -1;
        }
        else {
            direction = 1;
        }

        Point[] validMoves = new Point[1];

        validMoves[0] = new Point((int)this.getPos().getX(), (int)this.getPos().y + direction);
        return validMoves;
    }

}
