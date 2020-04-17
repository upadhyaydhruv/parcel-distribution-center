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

        g.setColor(Color.GRAY);
        if (direction==1){
            int[] xPoints = {x+width, (int) (x+width-Math.cos(45)*20), x, x, x+width};
            int[] yPoints = {y+length, (int) (y+length+Math.cos(45)*20), (int) (y+length+Math.cos(45)*20), y+length, y+length};
            g.fillPolygon(xPoints, yPoints, 4);
            g.fillArc(x+width-22, y+length-7, 22, 18, 10, -90);

            g.setColor(Color.BLACK);
            g.drawLine(x, (int) (y+Math.cos(45)*20+length), (int) (x+width-Math.cos(45)*20), (int) (y+Math.cos(45)*20)+length);
            g.drawArc(x+width-22, y+length-7, 22, 18, 10, -90);
        }
        else if (direction==2){
            int[] xPoints = {x, x, x+width, (int) (x+width-Math.cos(45)*20), (int) (x-Math.cos(45)*20), (int)(x-Math.cos(45)*20), x};
            int[] yPoints = {y, y+length, y+length, (int) (y+length+Math.cos(45)*20), (int) (y+length+Math.cos(45)*20), y, y};
            g.fillPolygon(xPoints, yPoints, 6);
            g.setColor(Color.GRAY);
            g.fillArc(x-18+width, y+length-8, 18, 18, 5, -98);
            g.setColor(Color.BLACK);
            g.drawArc(x-17, y+length-8, 17, 17, 0, -95);
            g.drawArc(x-18+width, y+length-8, 18, 18, 5, -98);
            g.drawLine((int) (x-Math.cos(45)*20), y, (int) (x-Math.cos(45)*20), (int) (y+length+Math.cos(45)*20));
            g.drawLine(x, y, x, y+length);
            g.drawLine(x, y+length, x+width, y+length);
            g.drawLine((int) (x-Math.cos(45)*20), (int) (y+length+Math.cos(45)*20), (int) (x-Math.cos(45)*20+width), (int) (y+length+Math.cos(45)*20));
            //g.drawLine(x,x,x,x);//TODO
        }

        else if (direction==3){
            int[] xPoints = {x, (int) (x-Math.cos(45)*20), (int) (x-Math.cos(45)*20), (int) (x-Math.cos(45)*20+width), x+width, x, x};
            int[] yPoints = {y, (int) (y+Math.cos(45)*20), (int) (y+Math.cos(45)*20+length), (int) (y+Math.cos(45)*20+length), y+length, y+length, y};
            g.fillPolygon(xPoints, yPoints, 6);
            g.setColor(Color.GRAY);
            g.fillArc((int)(x-Math.cos(45)*20), y, 21, 25, 90, 90);
            g.setColor(Color.BLACK);
            g.drawArc((int)(x-Math.cos(45)*20), y+length, 20, 20, 90, 90);
            g.drawArc((int)(x-Math.cos(45)*20), y, 21, 25, 90, 90);
            //g.drawLine(x, y, (int) (x-Math.cos(45)*20), (int) (y+Math.cos(45)*20));
            g.drawLine((int) (x-Math.cos(45)*20), (int) (y+Math.cos(45)*20), (int) (x-Math.cos(45)*20), (int) (y+Math.cos(45)*20+length));
            g.drawLine( (int) (x-Math.cos(45)*20), (int) (y+Math.cos(45)*20+length), (int) (x+width-Math.cos(45)*20), (int) (y+length+Math.cos(45)*20));
            g.drawLine(x, y+length, x+width, y+length);
        }

        else if (direction==4){
            g.setColor(Color.GRAY);
            int[] xPoints = {x, (int) (x-Math.cos(45)*20), (int) (x-Math.cos(45)*20), x, x};
            int[] yPoints = {y, (int) (y+Math.cos(45)*20), (int) (y+Math.cos(45)*20+length), y+length, y};
            g.fillPolygon(xPoints, yPoints, 4);
            g.setColor(Color.BLACK);
            g.drawLine((int) (x-Math.cos(45)*20), (int) (y+Math.cos(45)*20), (int)(x-Math.cos(45)*20), (int) (y+Math.cos(45)*20)+length);
            g.drawLine(x, y, x, y+length);
            g.drawArc(x-11, y+1, 17, 17, 90, 90);
            g.setColor(Color.GRAY);
            g.fillArc(x-11, y+1, 17, 17, 90, 90);
        }

        g.setColor(Color.WHITE);
        if (direction==1 || direction == 3) {
            for (int i = (int) (x - Math.cos(45)*20 + offset+1); i < x + width; i += 70) {
                g.drawLine(i, y, i, y + length);
            }
            for (int j=x+width-offset; j>x; j-=70){
                g.drawLine(j, (int) (y+length+Math.cos(45)*20), j, y+length);
            }
        }
        else if (direction==2){
            for (int i=(int) (y+length-offset+Math.cos(45)*20+2); i > y; i-=70){
                g.drawLine(x, i, x+width, i);
            }
            for (int j=y+offset; j<y+length; j+=70){
                g.drawLine((int) (x-Math.cos(45)*20), j, x, j);
            }
        }
        else if (direction==4){
            for (int j = y+length-offset; j>y; j-=70){
                g.drawLine((int) (x-Math.cos(45)*20), j, x, j);
            }
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
