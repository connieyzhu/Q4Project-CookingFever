import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class Coin{
	private int x,y; 
	private Image img;
	private AffineTransform tx;
	
	public Coin(int x, int y){
		this.x = x;
		this.y = y;
		
		img = getImage("/imgs/broccoli.png"); // change to batter
		
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actually picture location
		update();
		
		g2.drawImage(img, tx, null);
	
	}
	
	private void update() {
		tx.setToTranslation(x, y);

		//to scale it up or down to change size, .5 means 50% of original file
		tx.scale(1.0, 1.0);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.04,.04);
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
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	
	
}




