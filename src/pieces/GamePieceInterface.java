package pieces;


import java.awt.*;

public interface GamePieceInterface {

    void movePiece(Point newPos);

    Point[] validMoves();

    Color getColor();
    void setColor(Color color);

    Point getPos();
    void setPos(Point pos);
    int getX();
    int getY();

    Image getPicture();
}
