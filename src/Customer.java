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
	private int x1, x, y, num; //position
	private int sec = 1600;
	private int vx = -2;
	private String name;
	private Image img; 	
	private AffineTransform tx; 
	Timer timer;
	OrderTimer custTimer;
	Background order;
	Customer cust;
	Position pos;
	
	public Customer(int x, int y, String custName) {
		x1 = x+1200;
		this.x = x; 
		this.y = y;
		name = custName;
		timer = new Timer();
		num = ThreadLocalRandom.current().nextInt(0, 8);
		
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
		order = new Background(x-80, 70, "/imgs/Order Bubble.png");
		custTimer = new OrderTimer(x+10, 75, 1);
		pos = new Position("Person");
	
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x1, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		timer.schedule(new Wait(), 500*num);
		//call update to update the actually picture location
		g2.drawImage(img, tx, null);
		
		if(x1 == x) {
			//custTimer = new OrderTimer(x+10, 75, 1);
			custTimer.paint(g);
			order.paint(g);
		}
		
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
	    	if(x1 == x && custTimer.getTime()) {
	    		System.out.println("Customer Arrived!");
	    	}else if(!custTimer.getTime()) {
    			x1 += vx;
    			update();
    			timer.schedule(new RemindTask(), sec);
    		}else {
				x1 += vx;
				update();
				timer.schedule(new RemindTask(), sec);
			}
	    }
	}
	
	class Wait extends TimerTask {
	    public void run() {
	    	timer.schedule(new RemindTask(), sec);
	    }
	}
	
}


