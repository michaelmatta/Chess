import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Piece {

    private int x, y;
    private String id;
    private boolean color;

    public Piece(int x, int y, String id, boolean color) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.color = color;
    }

    public void move(int movetox, int movetoy, ArrayList<Piece> pieces) {
        if (color){ //if white
            for (int i = 0; i < pieces.size(); i++) {
                if (pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy) {
                    if (!pieces.get(i).isColor()){
                        pieces.remove(i);
                        i--;
                        x = movetox;
                        y = movetoy;
                    }
                }
                else{
                    x = movetox;
                    y = movetoy;
                }
            }
        }
        if (!color){ //if white
            for (int i = 0; i < pieces.size(); i++) {
                if (pieces.get(i).getX() == movetox && pieces.get(i).getY() == movetoy) {
                    if (pieces.get(i).isColor()){
                        pieces.remove(i);
                        i--;
                        x = movetox;
                        y = movetoy;
                    }
                }
                else{
                    x = movetox;
                    y = movetoy;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        try {
            BufferedImage pic = ImageIO.read(new File("res/" + id + ".png"));
            g2.drawImage(pic, x*Main.GRIDSIZE, y*Main.GRIDSIZE, 100, 100, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isColor() {
        return color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
