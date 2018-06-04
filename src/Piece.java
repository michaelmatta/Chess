import java.awt.*;
import java.util.ArrayList;

public class Piece {

    private int x, y;
    private String id, image;
    private boolean color;

    public Piece(int x, int y, String id, String image, boolean color) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.image = image;
        this.color = color;
    }

    public void move(int x, int y, ArrayList<Piece> pieces) {
        if (color){ //if white
            for (int i = 0; i < pieces.size(); i++) {
                if (pieces.get(i).getX() == x && pieces.get(i).getY() == y) {

                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        //draw image

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
