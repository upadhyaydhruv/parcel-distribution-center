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
            if (parcel.getX()+parcel.getLength()>=430 && parcel.getX()+parcel.getLength()<=530 && parcel.getY()>=240 && parcel.getY()<=330){
                scanning = true;
            }
        }
    }

    public void scanned(Parcel p){
        doneScanning = (p.getX()>=490);
    }

    public void isAligned(Parcel p){

    }

    public void scanColor(Parcel p){
        color = p.getColor();
    }

    public boolean isRunning(){
        return counter%2!=0;
    }

    public void updateSpeeds(Parcel p){
        if (counter%2!=0&&!doneScanning){
            p.setXVel(0);
            p.setYVel(0);
        }
        else if (counter%2==0&&!doneScanning){
            p.setXVel(1);
            p.setYVel(0);
        }
        if (doneScanning && p.getColor()==0){
            p.setXVel(0);
            p.setYVel(-1);
        }
        else if (doneScanning && p.getColor()==1){
            p.setXVel(1);
            p.setYVel(0);
        }
        else if (doneScanning && p.getColor()==2){
            p.setXVel(0);
            p.setYVel(1);
        }
    }


    public void paint(Graphics2D g){

        if (doneScanning&&color==0){
            g.drawImage(plane,10,492,null);
        }
        else if (doneScanning&&color==1){
            g.drawImage(truck,10,503,null);
        }
        else if (doneScanning&&color==2){
            g.drawImage(question,10,490,null);
        }

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
        //g.fillRect(x, y, 10, 10);


        if (scanning) {
            g.setColor(Color.RED);
            g.fillOval((int) (x+Math.cos(45)*20), (int) (y+Math.cos(45)*20), 10, 10);
            g.fillOval((int) (x+w-Math.cos(45)*20), (int) (y+Math.cos(45)*20), 10, 10);
            g.fillOval((int) (x+Math.cos(45)*20), (int) (y+l-Math.cos(45)*20), 10, 10);
            g.fillOval((int) (x+w-Math.cos(45)*20), (int) (y+l-Math.cos(45)*20), 10, 10);
        }

        //g.fillRect(x, y, l, w);

    }
}

