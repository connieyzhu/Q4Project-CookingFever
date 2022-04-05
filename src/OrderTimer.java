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

public class OrderTimer {
	private int x, y, gx, gy, tx, ty; //position
	//private Image img; 	
	//private AffineTransform tx; 
	
	public OrderTimer(int x, int y) {
		this.x = x; 
		this.y = y;
		gx = x;
		gy = y;
		tx = x-190;
		ty = y + 150;
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.gray);
		g.fillRect(gx, gy, tx, gy+150);
		
		g.setColor(Color.green);
		g.fillRect(x, y, tx, ty);
		
		update();
		
	}

	/* update the picture variable location */
	private void update() {
		
		if(ty <= gy+150) {
			y += 1;
			ty -= 1;
		}else {
			y = gy+150;
		}
		
		
	}

}


