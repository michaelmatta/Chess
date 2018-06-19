import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Main extends JPanel {
    public static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    public static final int GRIDSIZE = FRAMEWIDTH/8;

    private ArrayList<Piece> pieces;
    private Piece selectedPiece = null;
    private int turn;
    private boolean gameOn = false;
    private JMenuBar menuBar;

    public Main(){

        int[][] grid = new int[8][8];

        pieces = new ArrayList<>();

        setLayout(new FlowLayout(0));
        menuBar = new JMenuBar();
        JMenu m = new JMenu("MENU");

        JMenuItem test = new JMenuItem("restart");
        test.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                init();
                turn = 0;
                repaint();
            }
        });
        m.add(test);
        menuBar.add(m);
        menuBar.setAlignmentX(0);
        add(menuBar);


        //setLayout(null);

//        JInternalFrame t = new JInternalFrame("test");
//        add(t);
//        t.setVisible(true);
//        t.setBounds(200, 300, 400, 200);


//        JButton test = new JButton("TEST");
//        add(test);
        //testing.setBounds(200, 300, 400, 200);
        //test.setBounds(FRAMEWIDTH/2, FRAMEHEIGHT/2, 50, 50);


        init();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(gameOn) {
                    int x = e.getX() / GRIDSIZE;
                    int y = e.getY() / GRIDSIZE;
                    if (selectedPiece == null) {
                        for (Piece p : pieces) {
                            if (p.getX() == x && p.getY() == y) {
                                if (turn % 2 == 0 && p.isColor()) {
                                    selectedPiece = p;
                                    //highlighted1 = new Point(p.getX(), p.getY());
                                } else if (turn % 2 == 1 && !p.isColor()) {
                                    selectedPiece = p;
                                    //highlighted1 = new Point(p.getX(), p.getY());
                                }

                            }
                        }
                    } else {
                        for (int i = 0; i < pieces.size(); i++) {
                            if (pieces.get(i).getX() == selectedPiece.getX() && pieces.get(i).getY() == selectedPiece.getY()) {
                                Point temp = new Point(pieces.get(i).getX(), pieces.get(i).getY());
                                pieces.get(i).move(x, y, pieces);
                                if (temp.getX() != pieces.get(i).getX() || temp.getY() != pieces.get(i).getY()) {
                                    repaint();
                                    turn++;
                                    checkWin();
                                    //highlighted2 = new Point(pieces.get(i).getX(), pieces.get(i).getY());
                                }
                            }
                        }
                        selectedPiece = null;
                        //repaint();
                    }
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

    public void checkWin() {
        int blackC = 0;
        int whiteC = 0;
        for(Piece p: pieces) {
            if(p.isColor()) {
                if(p.getId().equals("KingWhite")) {
                    whiteC++;
                }
            }
            if(!p.isColor()) {
                if(p.getId().equals("KingBlack")) {
                    blackC++;
                }
            }
        }

        if(blackC == 0) {
            System.out.println("white wins");
            gameOn = false;
        } else if (whiteC == 0) {
            System.out.println("black wins");
            gameOn = false;
        }
    }

    public void init(){
        gameOn = true;
        pieces = new ArrayList<>();
        turn = 0;
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