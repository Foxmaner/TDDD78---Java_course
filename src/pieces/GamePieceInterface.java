package pieces;


import java.awt.*;
import java.util.ArrayList;

public interface GamePieceInterface {

    public void movePiece(Point newPos);

    public Point[] validMoves();

    public Color getColor();
    public void setColor(Color color);

    public Point getPos();
    public void setPos(Point pos);
    public int getX();
    public int getY();

    public Image getPicture();
}
