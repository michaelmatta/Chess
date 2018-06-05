import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Main extends JPanel {
    public static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    public static final int GRIDSIZE = FRAMEWIDTH/8;

    private ArrayList<Piece> pieces;
    private Piece selectedPiece = null;

    public Main(){

        int[][] grid = new int[8][8];

        pieces = new ArrayList<>();

        //initialize pieces
        init();

        //BEGIN GAME!!

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / GRIDSIZE;
                int y = e.getY() / GRIDSIZE;
                if(selectedPiece == null) {
                    for(Piece p: pieces) {
                        if(p.getX() == x && p.getY() == y) {
                            selectedPiece = p;
                            //System.out.println(selectedPiece);
                        }
                    }
                } else {
                    for (int i = 0; i < pieces.size(); i++) {
                        if(pieces.get(i).getX()==selectedPiece.getX() && pieces.get(i).getY()==selectedPiece.getY()){
                            pieces.get(i).move(x, y, pieces);
                            repaint();
                        }
                    }
                    selectedPiece = null;
                    //repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void init(){
        for (int i = 0; i < 8; i++) {
            pieces.add(new Piece(i, 6, "PawnWhite", true));
            pieces.add(new Piece(i, 1, "PawnBlack", false));
        }
        pieces.add(new Piece(0, 7, "RookWhite", true));
        pieces.add(new Piece(7, 7, "RookWhite", true));
        pieces.add(new Piece(0, 0, "RookBlack", false));
        pieces.add(new Piece(7, 0, "RookBlack", false));
        pieces.add(new Piece(1, 7, "KnightWhite", true));
        pieces.add(new Piece(6, 7, "KnightWhite", true));
        pieces.add(new Piece(1, 0, "KnightBlack", false));
        pieces.add(new Piece(6, 0, "KnightBlack", false));
        pieces.add(new Piece(2, 7, "BishopWhite", true));
        pieces.add(new Piece(5, 7, "BishopWhite", true));
        pieces.add(new Piece(2, 0, "BishopBlack", false));
        pieces.add(new Piece(5, 0, "BishopBlack", false));
        pieces.add(new Piece(4, 7, "KingWhite", true));
        pieces.add(new Piece(3, 7, "QueenWhite", true));
        pieces.add(new Piece(4, 0, "KingBlack", false));
        pieces.add(new Piece(3, 0, "Latifa", false));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) %2 == 1){
                    g2.setColor(new Color(94, 55, 5));
                }else{
                    g2.setColor(new Color(239, 188, 122));
                }
                g2.fillRect(GRIDSIZE * i, GRIDSIZE * j, GRIDSIZE, GRIDSIZE);
            }
        }

        for(Piece p: pieces) {
            p.draw(g2);
        }

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