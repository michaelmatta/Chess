import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JPanel {
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 1000;


    public Main(){

        int[][] grid = new int[8][8];

        ArrayList<Piece> pieces = new ArrayList<>();

        //initialize pieces
        //add pieces to arraylist

        //BEGIN GAME!!
    }

    public void PaintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

    }

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("Chess!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();


        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}