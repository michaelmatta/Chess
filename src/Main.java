import javax.swing.*;

public class Main extends JPanel {
    public static final int FRAMEWIDTH = 1000, FRAMEHEIGHT = 1000;

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("Chess!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

//        while(true) {
        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();


        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
//        }
    }
}