import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Central extends JPanel {

    private Parcel[] parcels = new Parcel[10];
    private Scanner scanner;
    private Conveyor belt1;
    private Conveyor belt2;

    private Central(){
        for (int i=0; i<parcels.length; i++){
            parcels[i] = new Parcel((int) (Math.random()*30)+20, (int) (Math.random()*30)+20, (int) (Math.random()*30)+20, (int)(Math.random()*3), i*-170, 270);
        }
        scanner = new Scanner(520, 370, 100, 100, 100);
        belt1 = new Conveyor(1, 0, 240, 450, 70);
        belt2 = new Conveyor(1, 520, 240, 500, 70);
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
            }
            @Override
            public void keyReleased(KeyEvent e){
                scanner.keyReleased(e);
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        setFocusable(true);
    }


    public void move() {
        for (Parcel parcel : parcels) {
            scanner.scanned(parcel);
            scanner.scanColor(parcel);
            scanner.updateSpeeds(parcel);
            belt1.setPaused(scanner);
            belt2.getParcelState(scanner);
            parcel.move();
        }
        belt1.move();
        belt2.move();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.GRAY);
        belt1.paint(g2d);
        scanner.paint(g2d);
        belt1.paint(g2d);
        belt2.paint(g2d);
        for (Parcel parcel:parcels) {
            scanner.scanned(parcel);
            scanner.scanColor(parcel);
            scanner.updateSpeeds(parcel);
            belt1.setPaused(scanner);
            belt2.getParcelState(scanner);
            parcel.paint(g2d);
            scanner.paint(g2d);
        }
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
