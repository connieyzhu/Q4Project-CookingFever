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

public class OrderTimer {
	private int x, y, gx, gy; //position
	private Image img; 	
	private AffineTransform tx; 

	public OrderTimer(int x, int y) {
		this.x = x; 
		this.y = y;
		gx = x;
		gy = y;
		//vy = -1;

		//tx = AffineTransform.getTranslateInstance(x, y);
		//init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.gray); //timer box
		g.fillRect(gx, gy, x, y);
		
		g.setColor(Color.green); //timer box
		g.fillRect(x, y, x+1, y+55);
		
		//call update to update the actually picture location
		update();
		//g2.drawImage(img, tx, null);
		

	}

	/* update the picture variable location */
	private void update() {
		
		y += 1;
		
		//tx.setToTranslation(x, y);

		//to scale it up or down to change size, .5 means 50% of original file
		//tx.scale(0.4, 0.4);
		
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		
		//to scale it up or down to change size, .5 means 50% of original file
		tx.scale(0.4, 0.4);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}


