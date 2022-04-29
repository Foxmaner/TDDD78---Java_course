package pieces;


import java.awt.*;

public abstract class GamePiece implements GamePieceInterface {
    protected Color color;
    protected Point pos;
    protected Image picture;

    public GamePiece(Color c, Point pos, Image picture){
        this.color = c;
        this.pos = pos;
        this.picture = picture;
    }

    @Override public void movePiece(Point newPos){
        this.pos = newPos;
    }

    @Override public Color getColor() {
        return color;
    }

    @Override public void setColor(Color color) {
        this.color = color;
    }

    @Override public Point getPos() {
        return pos;
    }

    @Override public void setPos(Point pos) {
        this.pos = pos;
    }


    @Override public Image getPicture() {
        return this.picture;
    }
}
