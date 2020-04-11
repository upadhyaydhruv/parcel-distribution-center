import java.awt.*;

public class Parcel {
    private int l, w, h;
    private int color;
    private int x, y;
    private int xVel = 1;
    private int yVel = 0;
    public Parcel(int l, int w, int h, int color, int startX, int startY){
       this.l = l;
       this.w = w;
       this.h = h;
       this.color = color;
       this.x = startX;
       this.y = startY;
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
        int[] xPoints = {x, x, x+l, (int)(x+l+Math.cos(45)*w), (int)(x+l+Math.cos(45)*w), (int)(x+l+Math.cos(45)*w-l), x};
        int[] yPoints = {y, y-h, y-h, (int)(y-h+Math.cos(45)*w), (int)(y-h+Math.cos(45)*w+h), (int)(y-h+Math.cos(45)*w+h), y};

        g.fillPolygon(xPoints, yPoints, 6);
        g.setColor(Color.BLACK);
        g.drawLine((int)(x+l+Math.cos(45)*w-l),(int)(y-h+Math.cos(45)*w+h), (int)(x+l+Math.cos(45)*w-l), (int)(y-h+Math.cos(45)*w));
        g.drawLine((int)(x+l+Math.cos(45)*w-l), (int)(y-h+Math.cos(45)*w),x, y-h);
        g.drawLine((int)(x+l+Math.cos(45)*w-l), (int)(y-h+Math.cos(45)*w), (int)(x+l+Math.cos(45)*w), (int)(y-h+Math.cos(45)*w));
        g.drawLine(x,y, x, y-h);
        g.drawLine(x,y-h,x+l,y-h);
        g.drawLine(x+l,y-h,(int)(x+l+Math.cos(45)*w),(int)(y-h+Math.cos(45)*w));
        g.drawLine((int)(x+l+Math.cos(45)*w),(int)(y-h+Math.cos(45)*w),(int)(x+l+Math.cos(45)*w),(int)(y-h+Math.cos(45)*w+h));
        g.drawLine((int)(x+l+Math.cos(45)*w),(int)(y-h+Math.cos(45)*w+h),(int)(x+l+Math.cos(45)*w-l),(int)(y-h+Math.cos(45)*w+h));
        g.drawLine((int)(x+l+Math.cos(45)*w-l),(int)(y-h+Math.cos(45)*w+h),x,y);
    }
}