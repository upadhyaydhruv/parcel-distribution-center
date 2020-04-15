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

    public Scanner(int x, int y, int length, int width, int height){
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
            if (parcel.getX()>=430 && parcel.getX()<=530 && parcel.getY()>=240 && parcel.getY()<=330){
                scanning = true;
            }
        }
    }

    public void scanned(Parcel p){
        doneScanning = (p.getX()>=490);
    }

    public boolean getScanned(){
        return doneScanning;
    }

    public int getColor(){
        return color;
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

        if (scanning){
            g.setColor(Color.RED);
            g.fillRect(475, 300, 20, 20);
        }

        if (doneScanning&&color==0){
            g.drawImage(plane,10,492,null);
        }
        else if (doneScanning&&color==1){
            g.drawImage(truck,10,503,null);
        }
        else if (doneScanning&&color==2){
            g.drawImage(question,10,490,null);
        }
    }
}

