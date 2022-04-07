import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class OrderTimer {
	private int x, y, gx, gy, tx, ty, sec; 
	Timer timer;

	    public OrderTimer(int x, int y, int seconds) {
	    	this.x = x; 
			this.y = y;
			gx = x;
			gy = y;
			tx = x-290;
			ty = y + 90;
			sec = seconds;
	    	
	    	timer = new Timer();
	        timer.schedule(new RemindTask(), seconds*400);
		}
	    
	    public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			
			g.setColor(Color.gray);
			g.fillRect(gx, gy, tx, gy+90);
			
			g.setColor(Color.green);
			g.fillRect(x, y, tx, ty);
			
		}
	    
	    class RemindTask extends TimerTask {
	        public void run() {
	        	if(y == gy+165) {
	        		System.out.println("Time's Up!");
	        	}else {
	    			y += 1;
	    			ty -= 1;
	    			timer.schedule(new RemindTask(), sec*400);
	    		}
	        }
	    }
}
