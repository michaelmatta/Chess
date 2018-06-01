import java.awt.*;

public class Piece {

    private int x, y;
    private String id, image;

    public Piece(int x, int y, String id, String image) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.image = image;
    }

    public void move(int x, int y) {
        if(isValidMove()) {

        }
    }

    public boolean isValidMove() {
        //if(id == "knight"){ move like a knight would}
        return true;
    }

    public void draw(Graphics2D g2) {
        //draw image
    }
}
