import java.awt.*;

public class Conveyor {
    private int direction;
    private int vel;
    private boolean on = false;
    private int y;
    private int x;
    private int offset = 0;
    private int width, length;
    private boolean paused = false;

    public Conveyor(int direction, int x, int y, int width, int length) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
    }

    public void move(Parcel[] p){
        this.on = false;
        this.vel = 1;
        for (Parcel parcel:p){
            if (parcel.getX()>this.x&&parcel.getX()<this.x+width&&parcel.getY()>this.y&&parcel.getY()<this.y+this.length){
                this.on = true;
            }
        }
        if (on&&!this.isPaused()){
            offset+=vel;
            if (offset>70){
                offset = 0;
            }
            }
        else {
            vel = 0;
        }
    }


    public void paint(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, length);
        g.setColor(Color.WHITE);
        if (direction==1 || direction == 3) {
            for (int i = x + offset; i < x + width; i += 70) {
                g.drawLine(i, y, i, y + length);
            }
        }
        else if (direction==2){
            for (int i=y + length -  offset; i > y; i-=70){
                g.drawLine(x, i, x+width, i);
            }
        }
        else if (direction==4){
            for (int i = y+offset; i<y+length; i+=70){
                g.drawLine(x, i, x+width, i);
            }
        }
    }

    public boolean isPaused() {
        return paused;
    }


    public void setPaused(Scanner s) {
        this.paused = s.isRunning();
    }
}
