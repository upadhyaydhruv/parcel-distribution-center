import java.awt.*;

public class Parcel {
    private int l, w, h;
    private int color;
    private int x, y;
    private int xVel = 1;
    private int yVel = 0;
    private int counter = 0;

    public Parcel(int l, int w, int h, int color, int startX, int startY){
       this.l = l;
       this.w = w;
       this.h = h;
       this.color = color;
       this.x = startX;
       this.y = startY;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setXVel(int xVel){
        this.xVel = xVel;
    }

    public void setYVel(int yVel){
        this.yVel = yVel;
    }

    public int getColor(){
        return this.color;
    }

    public void move(){
        this.x+=xVel;
        this.y+=yVel;
    }

    public void paint(Graphics2D g){
        if (color==0){
            g.setColor(Color.BLUE);
        }
        else if (color==1){
            g.setColor(Color.GREEN);
        }
        else if (color==2){
            g.setColor(Color.YELLOW);
        }

        int[] xPoints = {x, (int)(x-w*Math.cos(45)), (int)(x-w*Math.cos(45)), (int)(x-w*Math.cos(45)+l), x+l, x+l, x};
        int[] yPoints = {y, (int)(y-w*Math.cos(45)), (int) (y-w*Math.cos(45)-h), (int) (y-w*Math.cos(45)-h), y-h, y, y};

        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x+l, y);
        g.drawLine(x+l, y, x+l, y-h);
        g.drawLine(x+l, y-h, (int) (x+l-w*Math.cos(45)), (int)(y-h-w*Math.cos(45)));
        g.drawLine((int) (x+l-w*Math.cos(45)),(int)(y-h-w*Math.cos(45)), (int) (x+l-w*Math.cos(45))-l, (int)(y-h-w*Math.cos(45)));
        g.drawLine((int) (x+l-w*Math.cos(45))-l, (int)(y-h-w*Math.cos(45)),(int) (x+l-w*Math.cos(45))-l,(int)(y-w*Math.cos(45)));
        g.drawLine((int) (x+l-w*Math.cos(45))-l,(int)(y-w*Math.cos(45)),x, y);
        g.drawLine(x, y, x, y-h);
        g.drawLine(x, y-h, (int) (x-w*Math.cos(45)),(int) (y-h-w*Math.cos(45)));
        g.drawLine(x, y-h, x+l, y-h);
    }
}
