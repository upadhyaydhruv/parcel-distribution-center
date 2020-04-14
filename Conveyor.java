import java.awt.*;

public class Conveyor {
    private int direction;
    private int xVel;
    private int yVel;
    private boolean on;
    private int y;
    private int x;
    private int offset = 0;
    private int width, length;
    private int lineX, lineY;
    private boolean paused = false;
    private boolean twoInUse = false;
    private boolean threeInUse = false;
    private boolean fourInUse = false;



    public Conveyor(int direction, int x, int y, int width, int length) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
        this.lineX = x;
        this.lineY = y;

        //if (direction==1 || direction==3) {
        //    this.xVel = 1;
        //    this.yVel = 0;
        //}
        //else {
        //    this.xVel = 0;
        //    if (direction == 2) { // upwards conveyor
        //        this.yVel = -1;
        //    }
        //    if (direction == 4) {
        //        this.yVel = 1;
        //    }
        //}
    }

    public void move(){
        xVel = 1;
        yVel = 0;
        if (!paused && direction==1 || (twoInUse&&direction==2)){
            lineX+=xVel;
            if (lineX>70){
                lineX = 0;
            }
            lineY+=yVel;
        }

        else if (!twoInUse&&direction==2){
            xVel = 0;
        }
    }

    public void paint(Graphics2D g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, length);
        g.setColor(Color.WHITE);
        for (int i=x+lineX; i<x+width; i+=70){
            g.drawLine(i, y, i, y+length);
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void getParcelState(Scanner s){
        if (s.getScanned() && s.getColor()==0){
            twoInUse = true;
        }
        else if (s.getScanned() && s.getColor()==1){
            threeInUse = true;
        }
        else if (s.getScanned() && s.getColor()==2){
            fourInUse = true;
        }
    }

    public void setPaused(Scanner s) {
        this.paused = s.isRunning();
    }
}
