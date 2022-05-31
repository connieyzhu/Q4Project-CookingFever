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
import java.util.concurrent.ThreadLocalRandom;
 

public class Customer {
	private double x1, x, y, originalX1; //position
	private final int sec = 1600;
	private double vx;
	private double leavingVx = 0.5;
	private int count, numReturns;
	private String name;
	private boolean ready, done, returnDone, gameRestart;
	private Image img; 	
	private AffineTransform tx; 
	Position names;
	Timer timer;
	
	public Customer(int x, int x1, int y, String custName) {
		this.x1 = x1;
		this.x = x; 
		this.y = y;
		vx = 0;
		count = 0;
		numReturns = ThreadLocalRandom.current().nextInt(4, 7); 
		originalX1 = x1;
		ready = false;
		done = false;
		returnDone = false;
		gameRestart = false;
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
	
	public void setCount(int num) {
		count = num;
	}
	
	public void setVx(int num) {
		vx = num;
	}
	
	public boolean getReturnDone() {
		return returnDone;
	}
	
	public void setReturnDone(boolean x) {
		returnDone = x;
	}
	
	public void setReset(boolean x) {
		gameRestart = x;
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
	    	if(count == numReturns || gameRestart) {
	    		x1 = originalX1;
	    		vx = 0;
	    		leavingVx = 0;
	    		returnDone = true;
	    	}else if(x1 >= 2500){
	    		 done = false;
	    		 setName(names.getName());
	    		 x1 = originalX1;
	    		 count++;
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
	    		leavingVx = 0.5;
				update();
				timer.schedule(new RemindTask(), sec);
			}
	    }
	}
}


