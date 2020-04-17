import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Display {
    private BufferedImage plane;
    private BufferedImage truck;
    private BufferedImage question;
    private boolean doneScanning = false;
    private int color;

    public Display(){
        try{
            this.plane = ImageIO.read(this.getClass().getResource("plane_clipart.png"));
        } catch(IOException e){}

        try {
            this.truck = ImageIO.read(this.getClass().getResource("truck.png"));
        } catch (IOException e){}

        try {
            this.question = ImageIO.read(this.getClass().getResource("question.png"));
        } catch (IOException e) {}
    }

    public void paint(Graphics2D g, Scanner s){
        doneScanning = s.getDoneScanning();
        color = s.getColor();

        if (doneScanning&&color==0){
            g.drawImage(plane,1,492,null);
        }
        else if (doneScanning&&color==1){
            g.drawImage(truck,1,492,null);
        }
        else if (doneScanning&&color==2){
            g.drawImage(question,1,492,null);
        }


    }
}
