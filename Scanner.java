import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.event.KeyEvent.VK_SPACE;

public class Scanner {
    private int x;
    private int y;
    private int l, w;
    private boolean doneScanning = false;
    private boolean scanning = false;
    private BufferedImage plane = null;
    private BufferedImage truck = null;
    private BufferedImage question = null;
    private int color;
    private int counter;

    public Scanner(int x, int y, int length, int width){
        this.x = x;
        this.y = y;
        this.l = length;
        this.w = width;
        try{
            this.plane = ImageIO.read(new File("D:/Dhruv/plane_clipart.png"));
        } catch(IOException e){}

        try {
            this.truck = ImageIO.read(new File("D:/Dhruv/truck.png"));
        } catch (IOException e){}

        try {
            this.question = ImageIO.read(new File("D:/Dhruv/question.png"));
        } catch (IOException e) {}
    }

    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == VK_SPACE)
            counter++;
    }

    public void scanning(Parcel[] p){
        scanning = false;
        for (Parcel parcel:p){
            if (parcel.getX()+parcel.getLength()>=430 && parcel.getX()-(int) (parcel.getWidth()*Math.cos(45))<=530 && parcel.getY()>=240 && parcel.getY()-parcel.getHeight()<=330){
                scanning = true;
                parcel.setScanned(true);
            }
            else{
                parcel.setScanned(false);
            }
        }
    }

    public void scanned(Parcel p){
        doneScanning = (p.getX()>=490);
    }

    public void scanColor(Parcel p){
        color = p.getColor();
    }

    public boolean isPaused(){
        return counter%2!=0;
    }

    public void updateSpeeds(Parcel p){
        if (p.getScanning()) {
            if (!doneScanning) {
                p.setXVel(1);
                p.setYVel(0);
            }
            if (doneScanning && p.getColor() == 0) {
                p.setXVel(0);
                p.setYVel(-1);
            } else if (doneScanning && p.getColor() == 1) {
                p.setXVel(1);
                p.setYVel(0);
            } else if (doneScanning && p.getColor() == 2) {
                p.setXVel(0);
                p.setYVel(1);
            }
        }
        if (!p.getScanning()){
            if (doneScanning) {}
            else{
                if (counter%2!=0){
                    p.setXVel(0);
                    p.setYVel(0);
                }
                else {
                    p.setXVel(1);
                    p.setYVel(0);
                }
            }
        }
    }

    public boolean getDoneScanning(){
        return this.doneScanning;
    }

    public int getColor(){
        return this.color;
    }

    public void paint(Graphics2D g, Conveyor con){
        g.setColor(Color.BLACK);
        g.fillRect(137, 447, 45, 205);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(140, 450, 40, 200);
        g.setColor(Color.BLACK);
        g.fillRect(145, 505, 30, 30);
        g.setStroke(new BasicStroke(5));

        g.drawLine(0, 492, 101, 492);
        g.drawLine(101, 492, 101, 592);
        g.drawLine(101, 592, 0, 592);
        g.drawLine(0, 592, 0, 592);

        g.drawLine(137, 480, 120, 480);
        g.drawLine(120, 480, 120, 500);
        g.drawLine(120, 500, 101, 500);
        g.setStroke(new BasicStroke(1));
        if (this.isPaused() || !con.isOn()){
           g.setColor(Color.RED);
        }
        else {
            g.setColor(Color.GREEN);
        }
        g.fillRect(150, 510, 20, 20);

        g.setColor(Color.BLACK);

        int[] xPoints1 = {x, (int) (x+Math.cos(45)*40), (int) (x+Math.cos(45)*40), x, x};
        int[] yPoints1 = {y, (int) (y+Math.cos(45)*40), (int) (y+l-Math.cos(45)*40),y+l, y};
        g.fillPolygon(xPoints1, yPoints1, 4);
        int[] xPoints2 = {x, (int) (x+Math.cos(45)*40), (int) (x+w-Math.cos(45)*40), x+w, x};
        int[] yPoints2 = {y+l, (int) (y+l-Math.cos(45)*40), (int) (y+l-Math.cos(45)*40), y+l, y+l};
        g.fillPolygon(xPoints2, yPoints2, 4);
        int[] xPoints3 = {(int) (x+w-Math.cos(45)*40), (int) (x+w-Math.cos(45)*40), x+w, x+w, (int) (x+w-Math.cos(45)*40)};
        int[] yPoints3 = {(int) (y+l-Math.cos(45)*40), (int) (y+Math.cos(45)*40), y, y+l, (int) (y+l-Math.cos(45)*40)};
        g.fillPolygon(xPoints3, yPoints3, 4);
        int[] xPoints4 = {x, x+w, (int) (x+w-Math.cos(45)*40), (int) (x+Math.cos(45)*40), x};
        int[] yPoints4 = {y, y, (int) (y+Math.cos(45)*40), (int) (y+Math.cos(45)*40), y};
        g.fillPolygon(xPoints4, yPoints4, 4);

        Color c = new Color(0, 0, 128, 50);
        g.setColor(c);
        g.fillRect((int) (x+Math.cos(45)*40), (int) (y+Math.cos(45)*40), (int) (w-2*Math.cos(45)*40), (int) (w-2*Math.cos(45)*40));
        g.fillRect(x, y, 10, 10);


        if (scanning) {
            g.setColor(Color.RED);
            g.fillOval((int) (x+Math.cos(45)*20)-5, (int) (y+Math.cos(45)*20)-5, 10, 10);
            g.fillOval((int) (x+w-Math.cos(45)*20)-5, (int) (y+Math.cos(45)*20)-5, 10, 10);
            g.fillOval((int) (x+Math.cos(45)*20)-5, (int) (y+l-Math.cos(45)*20)-5, 10, 10);
            g.fillOval((int) (x+w-Math.cos(45)*20)-5, (int) (y+l-Math.cos(45)*20)-5, 10, 10);
        }
    }
}
