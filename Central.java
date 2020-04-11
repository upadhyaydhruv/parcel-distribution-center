import javax.swing.*;
import java.awt.*;

public class Central extends JPanel {

    private Parcel test;

    public Central(){
        test = new Parcel(50, 70, 40, 2, 230, 200);
    }

    public void move(){
        test.move();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        test.paint(g2d);

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("GoodManMcGee");
        frame.setSize(1020,640);
        Central panel = new Central();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true){
            panel.move();
            panel.repaint();
            Thread.sleep(10);
        }

    }
}
