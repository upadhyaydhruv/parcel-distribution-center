import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Central extends JPanel {

    private Parcel[] parcels = new Parcel[10];
    private Scanner scanner;
    private Conveyor belt1;
    private Conveyor belt2;
    private Conveyor belt3;
    private Conveyor belt4;
    private Display screen;

    private Central(){
        for (int i=0; i<parcels.length; i++){
            parcels[i] = new Parcel((int) (Math.random()*30)+20, (int) (Math.random()*30)+20, (int) (Math.random()*30)+20, (int)(Math.random()*3), i*-170, 290);
        }
        scanner = new Scanner(435, 220, 120, 120);
        belt1 = new Conveyor(1, 0, 240, 430, 70);
        belt2 = new Conveyor(3, 570, 240, 480, 70);
        belt3 = new Conveyor (2, 465, 0, 70, 200);
        belt4 = new Conveyor(4, 465, 350, 70, 240);
        screen = new Display();
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
            scanner.scanColor(parcel);
            scanner.scanned(parcel);
            scanner.updateSpeeds(parcel);
            belt1.setPaused(scanner);
            parcel.move();
        }
        belt2.move(parcels);
        belt1.move(parcels);
        belt3.move(parcels);
        belt4.move(parcels);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        belt1.paint(g2d);
        belt2.paint(g2d);
        scanner.scanning(parcels);
        scanner.paint(g2d, belt1);
        belt2.paint(g2d);
        belt1.paint(g2d);
        belt3.paint(g2d);
        belt4.paint(g2d);

        for (Parcel parcel:parcels) {
            scanner.scanned(parcel);
            scanner.scanColor(parcel);
            scanner.updateSpeeds(parcel);
            screen.paint(g2d, scanner);
            belt1.setPaused(scanner);
            parcel.paint(g2d);
            scanner.paint(g2d, belt1);
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
