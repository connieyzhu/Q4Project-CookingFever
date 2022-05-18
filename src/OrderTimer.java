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

public class OrderTimer {
	private int x, y, gx, gy, tx, ty, sec, personX, ogT, ogTy, secondsAmt; 
	Timer timer;
	Customer cust;
	Position name;
	Background order;
	Position waiting;
	Position orderItem;
	private int count = 0;
	private String custName;
	Customer cust1;
	Object[] orders;
	Object item;
	Coin coin;
	private int totalMoney = 0;
		
	    public OrderTimer(int x, int y, int seconds, int wait) {
	    	personX = x-10;
	    	System.out.println("person " + personX);
	    	this.x = x; 
	    	this.y = y;
			gx = x;
			gy = y;
			tx = x-(x-10);
			ty = y + 100;
			sec = seconds;
			ogT = y;
			ogTy = y + 100;
			orderItem = new Position("orderItem");
			orders = new Object[ThreadLocalRandom.current().nextInt(1, 4)];
			
			int itemX = x-75;
			int itemY = y+5;
			for(int i = 0; i < orders.length; i++) {
				double scale = 0.5;
				String newOrderItem = orderItem.getItem();
				if(newOrderItem.equals("CoffeeOrder")) {
					itemX = x-70;
					scale = 0.75;
					totalMoney += 4;
				}else {
					totalMoney += 15;
				}
				item = new Object(itemX, itemY, newOrderItem, scale);
				orders[i] = item;
				itemX = x-75;
				itemY += 50;
			}
			
			if(orders.length == 1) {
				if(orders[0].getType().equals("CoffeeOrder")) {
					secondsAmt = 200;
				}
				secondsAmt = 350;
			}
			if(orders.length == 2) {
				secondsAmt = 600;
			}
			if(orders.length == 3) {
				secondsAmt = 900;
			}
	    	 
			waiting = new Position("wait");
			name = new Position("name");
			custName = name.getName();
			order = new Background(x-90, 70, "/imgs/Order Bubble.png");
			cust = new Customer(personX, wait, 130, custName);
	    	timer = new Timer();
	    	coin = new Coin(personX+70, 300);
		}
	    
	    public void runner() {
	    	if(count < 1) {
	    		timer.schedule(new RemindTask(), sec*secondsAmt);
	    		count++;
	    	}
	    }
	    
	    public Object[] custOrder() {
	    	return orders;
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
				
				for(int i = 0; i < orders.length; i++) {
					orders[i].paint(g2);
				}
				
				runner();
			}
				cust.paint(g2);
				
				if(coin.getCollect()) {
					coin.paint(g2);
				}
	    }
	    
	    public void done(boolean x) {
	    	cust.setDone(x);
	    }
	    
	    public void generateNewOrder() {
	    	int itemX = x-75;
			int itemY = y+5;
			totalMoney = 0;
			for(int i = 0; i < orders.length; i++) {
				double scale = 0.5;
				String newOrderItem = orderItem.getItem();
				if(newOrderItem.equals("CoffeeOrder")) {
					itemX = x-70;
					scale = 0.75;
					totalMoney += 4;
				}else {
					totalMoney += 15;
				}
				item = new Object(itemX, itemY, newOrderItem, scale);
				orders[i] = item;
				itemX = x-75;
				itemY += 50;
			}
	    }
	    
	    public Coin getCoin() {
	    	return coin;
	    }
	    
	    public int getTotal() {
	    	return totalMoney;
	    }
	    
	    class RemindTask extends TimerTask {
	        public void run() {
	        	if(y == gy+165) {
	        		done(true);
	        		y = ogT;
	        		ty = ogTy;
	        		count = 0;
	        		coin.setCollect(true);
	        		generateNewOrder();
	        	}else {
	    			y += 1;
	    			ty -= 1;
	    			timer.schedule(new RemindTask(), sec*secondsAmt); //400
	    		}
	        }
	    }
	  
}
