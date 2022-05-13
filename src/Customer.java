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
 

public class Customer {
	private double x1, x, y, originalX1; //position
	private final int sec = 1600;
	private double vx = 1;
	private double leavingVx = 0.5;
	private String name;
	private boolean ready, done;
	private Image img; 	
	private AffineTransform tx; 
	Position names;
	Timer timer;
	
	public Customer(int x, int x1, int y, String custName) {
		this.x1 = x1;
		System.out.println("Pos " + x1);
		this.x = x; 
		this.y = y;
		originalX1 = x1;
		ready = false;
		done = false;
		name = custName;
		names = new Position("name");
		if(name == "Daphne") {
			img = getImage("/imgs/D1.png"); 
		}
		if(name == "Kyle") {
			img = getImage("/imgs/K1.png"); 
		}
		if(name == "Francis") {
			img = getImage("/imgs/F1.png"); 
		}
		if(name == "Linda") {
			img = getImage("/imgs/L1.png"); 
		}
		
		tx = AffineTransform.getTranslateInstance(x1, y);
		init(x1, y); 				//initialize the location of the image
									//use your variables
		timer = new Timer();
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x1, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		timer.schedule(new RemindTask(), sec);
		//call update to update the actually picture location
		g2.drawImage(img, tx, null);
		update();

	}
	
	public boolean getReady() {
		return ready;
	}

	public void setName(String newName) {
		name = newName;
		if(name == "Daphne") {
			img = getImage("/imgs/D1.png"); 
		}
		if(name == "Kyle") {
			img = getImage("/imgs/K1.png"); 
		}
		if(name == "Francis") {
			img = getImage("/imgs/F1.png"); 
		}
		if(name == "Linda") {
			img = getImage("/imgs/L1.png"); 
		}
	}
	
	
	public void setDone(boolean x) {
		done = x;
	}

	/* update the picture variable location */
	private void update() {
		
	 
		tx.setToTranslation(x1, y);

		//to scale it up or down to change size, .5 means 50% of original file
		tx.scale(0.4, 0.4);
		
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
	
	class RemindTask extends TimerTask {
	    public void run() {
	    	if(x1 >= 2000){
	    		 done = false;
	    		 setName(names.getName());
	    		 x1 = originalX1;
	    		 timer.schedule(new RemindTask(), sec);
	    	}else if(done){ 
	    		x1 += leavingVx;
	    		ready = false;
				update();
				timer.schedule(new RemindTask(), sec);
	    	}else if(x1 == x) {
	    		ready = true;
	    		timer.schedule(new RemindTask(), sec);
	    	}else if(x1 < x){
	    		x1 += vx;
				update();
				timer.schedule(new RemindTask(), sec);
			}
	    }
	}
}


