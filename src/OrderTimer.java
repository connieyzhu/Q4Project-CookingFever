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
	private int x, y, gx, gy, tx, ty, sec, personX; 
	Timer timer;
	Customer cust;
	Position name;
	Position wait;
	Background order;
	private boolean time;
	private boolean restart;
	private int count = 0;
	private String custName;
	
		public OrderTimer() {
			time = false;
		}
	    public OrderTimer(int x, int y, int seconds) {
	    	personX = x-10;
	    	this.x = x; 
			//this.y = y-15;
	    	this.y = y;
	    	time = false;
	    	restart = false;
			gx = x;
			gy = y;
			tx = x-(x-10);
			ty = y + 100;
			sec = seconds;
	    	
			wait = new Position("wait");
			name = new Position("name");
			custName = name.getName();
			order = new Background(x-90, 70, "/imgs/Order Bubble.png");
			cust = new Customer(personX, 130, custName);
	    	timer = new Timer();
	    	//timer.schedule(new RemindTask(), sec*8000);
	 
	    	
		}
	    
	    public void runner() {
	    	if(count < 1) {
	    		timer.schedule(new RemindTask(), sec*400);
	    		count++;
	    	}
	    }
	    
	    public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			
			if(cust.getReady()) {
				g.setColor(Color.gray);
				g.fillRect(gx, gy, tx, gy+90);
				
				g.setColor(Color.green);
				if(y >= 180 && y < 210) {
					g.setColor(Color.yellow);
					//cust.changePicture("/imgs/" + custName.substring(0,1) + "2.png");
				}else if(y >= 200) {
					g.setColor(Color.red);
					//cust.changePicture("/imgs/" + custName.substring(0,1) + "3.png");
				}
				g.fillRect(x, y, tx, ty);
				order.paint(g);
				runner();
			}
			
			cust.paint(g2);
			
		}
	    
	    public void updateLeave(String x) {
	    	if(x == "leave") {
	    		double newX = cust.getX1() + cust.getVX();
	        	cust.setX1(newX);
	    	}
	    	if(x == "return") {
	    		double newX = wait.getWait();
	        	cust.setX1(newX);
	        	cust.setName(name.getName());
	        	restart = true;
	        	
	        	System.out.println("should return");
	    	}
	    }
	    
	    class RemindTask extends TimerTask {
	        public void run() {
	        	if(time == false && y == gy+165) {
	        		restart = false;
	        		timer.schedule(new Leave(), sec*400); //400
	        	}else if(y == gy+165) {
	        		System.out.println("Time's Up!");
	        		time = false;
	        	}else {
	    			y += 1;
	    			ty -= 1;
	    			time = true;
	    			timer.schedule(new RemindTask(), sec*400); //400
	    		}
	        }
	    }
	    
	    class Leave extends TimerTask {
	    	 public void run() {
		        	if(cust.getX1() <= cust.getX()-2000) {
		        		updateLeave("leave");
		        		timer.schedule(new Leave(), sec*400);
		        	}else {
		        		updateLeave("return");
		        		cancel();
		        	}
		        }
	    }
	    public boolean getTime() {
	    	return time;
	    }
}
