import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Image;

public class Background {
	public double x, y; //position
	private Image img; 	
	private AffineTransform tx; 

	public Background(double x, double y) {
		this.x = x; 
		this.y = y;
		img = getImage("/imgs/Background-01.png"); 

		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actually picture location
		update();
		g2.drawImage(img, tx, null);
		

	}

	/* update the picture variable location */
	private void update() {

		tx.setToTranslation(x, y);

		//to scale it up or down to change size, .5 means 50% of original file
		tx.scale(4.5, 4.5);
		
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		
		//to scale it up or down to change size, .5 means 50% of original file
		tx.scale(4.5, 4.5);
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

