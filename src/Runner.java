/*
good job
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.tools.DocumentationTool.Location;

import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
/*
 * really fun and pretty challenging, nice art a bit confusing at first
 * well made, i'm rly stressed out tho
 * v fun, just like the actual game, the homie in the glasses was hawt
 * so cute
 *good effort
 *nice improvement from last game
 *good graphics
 *omfg you slayed love you bestie
 *ate
 */
public class Runner extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
	
	private static ArrayList<Object> objectList;
	private Object hitBox;
    private Point offset;
    
    private boolean spot1 = false;
    private boolean spot2 = false;
    private boolean spot3 = false;
    private boolean spot4 = false;
    
    private boolean oven1 = false;
    private boolean oven2 = false;
    private boolean oven3 = false;
    private boolean oven4 = false;
	
	Background cafeBg = new Background(0, 0, "/imgs/CafeBG.png");
	Background cafeCounter = new Background(0, 0, "/imgs/CafeCounter.png");
	
	Position pos = new Position("timer");
	Position posWait = new Position("wait");
	
	OrderTimer timer = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer1 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer2 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer3 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	
	Object coffee1 = new Object(195, 485, "CoffeeEmpty", 1.0);
	Object coffee2 = new Object(255, 490, "CoffeeEmpty", 1.0);
	Object coffee3 = new Object(315, 495, "CoffeeEmpty", 1.0);
	
	Music music = new Music();
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	
	private boolean restart = true; 
	private boolean playAgain = false;
	private boolean tutorial = false;
	
	private boolean coffee1Selected = false;
	private boolean coffee2Selected = false;
	private boolean coffee3Selected = false;

	int total = 0;
	
	public static int count = 0; 
	
	public static void addCount(int num) {
		count += num;
	}
	 
	public void paint(Graphics g) {
		cafeBg.paint(g);
		cafeCounter.paint(g);
		timer.paint(g);
		timer1.paint(g);
		timer2.paint(g);
		timer3.paint(g);
		
		Music.play();
		checkSpots();
		checkOven(); 
		setOvenDrag();
	
		//scoring
		g.setColor(Color.black);
		g.setFont(new Font("Serif", Font.PLAIN, 30));
		g.drawString(count + "/3 customers lost", 10, 47);
		g.setColor(Color.WHITE);
		g.drawString(total + "", 600, 47);
				
		if((timer.custDone() && timer1.custDone() && timer2.custDone() && timer3.custDone()) || count == 3) {
			timer.reset(true);
			timer1.reset(true);
			timer2.reset(true);
			timer3.reset(true);
			OrderTimer.timerReset(true);
			playAgain = true;
		}
		//start button
		if(restart) {
			playAgain = false;
			timer.reset(false);
			timer1.reset(false);
			timer2.reset(false);
			timer3.reset(false);
			timer.setReturnDoneCust(false);
			timer1.setReturnDoneCust(false);
			timer2.setReturnDoneCust(false);
			timer3.setReturnDoneCust(false);
			OrderTimer.timerReset(false);
			g.fillRect(1165, 20, 100, 40);
			g.setFont(new Font("Serif", Font.PLAIN, 30));
			g.setColor(Color.black);
			g.drawString("Start", 1185, 50);
						
			if(!tutorial) {
				g.setColor(Color.WHITE);
				g.fillRect(1165, 80, 100, 40);
				g.setFont(new Font("Serif", Font.PLAIN, 30));
				g.setColor(Color.black);
				g.drawString("Tutorial", 1167, 110);
			}
						
			if(tutorial) {
				g.setColor(Color.WHITE);
				g.fillRect(200, 400, 110, 70); //coffee
				g.fillRect(560, 415, 125, 70); //cake batter
				g.fillRect(1000, 350, 110, 90); //bake cake
				g.fillRect(200, 620, 90, 70); // trash
				g.fillRect(400, 240, 180, 130); // customers
				g.fillRect(300, 15, 150, 90); //lost customer count
							
				g.setFont(new Font("Serif", Font.PLAIN, 15));
							
				g.setColor(Color.black);
				g.drawString("1: Customers ", 410, 260);
				g.drawString("complete requested order ", 410, 280);
				g.drawString("within the time frame", 410, 300);
				g.drawString("by dragging items to them", 410, 320);
				g.drawString("and click on earned money", 410, 340);
				g.drawString("from completing their orders", 410, 360);
							
				g.drawString("2: Coffee ", 210, 420);
				g.drawString("click on machine", 210, 440);
				g.drawString("wait 5 seconds", 210, 460);
							
				g.drawString("3: Cake Batter ", 570, 435);
				g.drawString("drag batter & fruit", 570, 455);
				g.drawString("onto tray", 570, 475);
							
				g.drawString("4: Bake Cake ", 1010, 370);
				g.drawString("drag batter to ", 1010, 390);
				g.drawString("available oven ", 1010, 410);
				g.drawString("wait 10 seconds", 1010, 430);
							
				g.drawString("5: Trash Can ", 210, 640);
				g.drawString("drag items ", 210, 660);
				g.drawString("to dispose", 210, 680);
							
				g.drawString("6: Satisfied Customers ", 310, 35);
				g.drawString("If 3 customers leave", 310, 55);
				g.drawString("when time runs out, ", 310, 75);
				g.drawString("the game is over", 310, 95);
							
			}
						
			total = 0;
			count = 0;
			timer.restartCount(0);
			timer1.restartCount(0);
			timer2.restartCount(0);
			timer3.restartCount(0);
							
		}else if(playAgain) {
			for(int i = 0; i < objectList.size(); i++) {
				objectList.remove(i);
			}
			objectList.add(coffee1);
			objectList.add(coffee2);
			objectList.add(coffee3);
					
			g.setColor(Color.PINK);
			g.fillRect(0, 0, 1280, 750);
						
			g.setColor(Color.black);
			g.setFont(new Font("Serif", Font.PLAIN, 40));
			g.drawString("Exit Out to Play Again", 460, 370);
			if(count == 3) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif", Font.PLAIN, 90));
				g.drawString("Nice Try: Game Over", 250, 300);
			}else {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Serif", Font.PLAIN, 90));
				g.drawString("Success! Earned " + total + " coins!", 250, 300);
			}
		}
		if(!playAgain) {
			for(Object obj:objectList) {
				obj.paint(g);
			}
		}
	}
	
	public static void main(String[] args) {
		Runner f = new Runner();
		
	}
	
	public Runner() {
		JFrame f = new JFrame("Cooking Fever");
 		f.setSize(new Dimension(1280, 750));
		f.add(this);
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		f.addKeyListener(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		objectList = new ArrayList<>();
		objectList.add(coffee1);
		objectList.add(coffee2);
		objectList.add(coffee3);
	}
	
	public void checkOven() {
		for(int i = 3; i< objectList.size(); i++) {
			if(objectList.get(i).getX() == 970 && objectList.get(i).getY() == 375) {
				oven1 = true;
				break;
			}else {
				oven1 = false;
			}
		}
		for(int i = 3; i< objectList.size(); i++) {
			if(objectList.get(i).getX() == 950 && objectList.get(i).getY() == 452) {
				oven2= true;
				break;
			}else {
				oven2 = false;
			}
		}
		for(int i = 3; i< objectList.size(); i++) {
			if(objectList.get(i).getX() == 933 && objectList.get(i).getY() == 530) {
				oven3= true;
				break;
			}else {
				oven3 = false;
			}
		}
		
		for(int i = 3; i< objectList.size(); i++) {
			if(objectList.get(i).getX() == 920 && objectList.get(i).getY() == 609) {
				oven4= true;
				break;
			}else {
				oven4 = false;
			}
		}
	}
	
	
	public void checkSpots() {
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).getX() == 515 && objectList.get(i).getY() == 370) {
				spot1 = true;
				break;
			}else {
				spot1 = false;
			}
		}
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).getX() == 505 && objectList.get(i).getY() == 440) {
				spot2= true;
				break;
			}else {
				spot2 = false;
			}
		}
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).getX() == 641 && objectList.get(i).getY() == 370) {
				spot3= true;
				break;
			}else {
				spot3 = false;
			}
		}
		for(int i = 0; i < objectList.size(); i++) {
			if(objectList.get(i).getX() == 645 && objectList.get(i).getY() == 440) {
				spot4= true;
				break;
			}else {
				spot4 = false;
			}
		}
	}
	
	
	public static ArrayList<Object> getObjectList(){
		return objectList;
	}
	
	public static Object getObject(int index) {
		return objectList.get(index);
	}
	
	public void setOvenDrag() {
		for(Object obj: objectList) {
			if(obj.getType().equals("ChocBlueOven")||obj.getType().equals("ChocStrawOven")
					||obj.getType().equals("VanBlueOven")||obj.getType().equals("VanStrawOven")) {
				obj.setDrag(false);
			}else {
				obj.setDrag(true);
			}
		}
	}
	
	//@Override
	public void mouseClicked(MouseEvent arg0) {
		if(restart) {
			if(arg0.getX() >= 1165 && arg0.getX() <= 1268 && arg0.getY() >= 20 && arg0.getY() <= 85) {
				System.out.println("game starting");
				timer.setCustVx(1);
				timer1.setCustVx(1);
				timer2.setCustVx(1);
				timer3.setCustVx(1);
				tutorial = false;
				restart = false;
			}else if(arg0.getX() >= 1165 && arg0.getX() <= 1268 && arg0.getY() >= 80 && arg0.getY() <= 145) { 
				tutorial = true;
			}
		}
		if(timer.getCoin().getCollect()) {
			int x = timer.getCoin().getX();
			int y = timer.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				timer.getCoin().setCollect(false);
				total += timer.getTotal();
			}
		}
		
		if(timer1.getCoin().getCollect()) {
			int x = timer1.getCoin().getX();
			int y = timer1.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				timer1.getCoin().setCollect(false);
				total += timer1.getTotal();
			}
		}
		
		if(timer2.getCoin().getCollect()) {
			int x = timer2.getCoin().getX();
			int y = timer2.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				timer2.getCoin().setCollect(false);
				total += timer2.getTotal();
			}
		}
		
		if(timer3.getCoin().getCollect()) {
			int x = timer3.getCoin().getX();
			int y = timer3.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				timer3.getCoin().setCollect(false);
				total += timer3.getTotal();
			}
		}
		if(arg0.getX() >= 170 && arg0.getX() <= 170+210 && arg0.getY() >= 360 && arg0.getY() <= 360 + 200) {
			new CoffeeTimer(5);
			System.out.println("Coffee Maker Starting: Done in 5 seconds");	
		}
		
	}
		
	
	//@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	//@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	//@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getX()>=505 && arg0.getX()<= 635 && arg0.getY() >= 640 && arg0.getY() <= 705) {
			objectList.add(new Object(505, 620, "ChocBatter", 1.0));
		}
		if(arg0.getX()>=645 && arg0.getX()<= 775 && arg0.getY() >= 640 && arg0.getY() <= 705) {
			objectList.add(new Object(645, 620, "VanBatter", 1.0));
		}
		if(arg0.getX()>=550 && arg0.getX()<= 630 && arg0.getY() >= 550 && arg0.getY() <= 630) {
			objectList.add(new Object(550, 550, "Strawberry", 1.0));
		}
		if(arg0.getX()>=660 && arg0.getX()<= 740 && arg0.getY() >= 550 && arg0.getY() <= 630) {
			objectList.add(new Object(660, 550, "Blueberry", 1.0));
		}
		
		Point mp = arg0.getPoint();
		for (Object box : objectList) {
			if (box.getBounds().contains(mp) && box.getDrag()) {
				hitBox = box;
				offset = new Point();
				offset.x = mp.x - box.getBounds().x;
				offset.y = mp.y - box.getBounds().y;
			}
		}
		if(hitBox == null) {
			return;
		}
		if(arg0.getX()>=915 && arg0.getX()<=1130 && arg0.getY()>=375 && arg0.getY()<=700){
			if(hitBox.getType().equals("VanStrawBakeOven")) {
				objectList.get(hitBox.getIndex()).fullBakeChange();
			}else if(hitBox.getType().equals("VanBlueBakeOven")) {
				objectList.get(hitBox.getIndex()).fullBakeChange(); 
			}else if(hitBox.getType().equals("ChocStrawBakeOven")) {
				objectList.get(hitBox.getIndex()).fullBakeChange(); 
			}else if(hitBox.getType().equals("ChocBlueBakeOven")) {
				objectList.get(hitBox.getIndex()).fullBakeChange(); 
			}
		}

		if(arg0.getX() >= 195 && arg0.getX() <= 195+60 && arg0.getY() >= 485 && arg0.getY() <= 485+60) {
			coffee1Selected = true;
		}else if(arg0.getX() >= 255 && arg0.getX() <= 255+60 && arg0.getY() >= 490 && arg0.getY() <= 490+60) {
			coffee2Selected = true;
		}else if(arg0.getX() >= 315 && arg0.getX() <= 315+60 && arg0.getY() >= 495 && arg0.getY() <= 495+60) {
			coffee3Selected = true;
		}
		
	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(hitBox == null) {
			return;
		}
		Point mp = arg0.getPoint();
		int px = arg0.getX();
		int py = arg0.getY();

		for(int i = 0; i < objectList.size(); i++) {
			if(hitBox.getType().equals("Blueberry") || hitBox.getType().equals("Strawberry")) {
				for(Object batter:objectList) {
					if(batter.getType().equals("VanBatter")||batter.getType().equals("ChocBatter")){
						if(batter.getBounds().contains(mp)) {
							if(hitBox.getType().equals("Blueberry")) {
								batter.addFruitChange("Blueberry");
							}else if(hitBox.getType().equals("Strawberry")) {
								batter.addFruitChange("Strawberry");
							}
						}
					}
				}
				objectList.remove(hitBox);
			}
			if(timer.itemIsInside(px, py)) {
				for(int j = 0; j < timer.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals("CoffeeFull") && timer.custOrder().get(j).getType().equals("CoffeeOrder") && hitBox.getType().equals("CoffeeFull")) {
						if(i == 0 && coffee1Selected) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
							coffee1Selected = false;
						}else if(i == 1 && coffee2Selected) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
							coffee2Selected = false;
						}else if(i == 2 && coffee3Selected) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
							coffee3Selected = false;
						}
						break;
					}
					if(objectList.get(i).getType().equals(timer.custOrder().get(j).getType())) {
						timer.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer1.itemIsInside(px, py)) {
				for(int j = 0; j < timer1.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals("CoffeeFull") && timer1.custOrder().get(j).getType().equals("CoffeeOrder") && hitBox.getType().equals("CoffeeFull")) {
						if(i == 0 && coffee1Selected) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
							coffee1Selected = false;
						}else if(i == 1 && coffee2Selected) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
							coffee2Selected = false;
						}else if(i == 2 && coffee3Selected) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
							coffee3Selected = false;
						}
						break;
					}
					if(objectList.get(i).getType().equals(timer1.custOrder().get(j).getType())) {
						timer1.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer2.itemIsInside(px, py)) {
				for(int j = 0; j < timer2.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals("CoffeeFull") && timer2.custOrder().get(j).getType().equals("CoffeeOrder") && hitBox.getType().equals("CoffeeFull")) {
						if(i == 0 && coffee1Selected) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
							coffee1Selected = false;
						}else if(i == 1 && coffee2Selected) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
							coffee2Selected = false;
						}else if(i == 2 && coffee3Selected) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
							coffee3Selected = false;
						}
						break;
					}
					if(objectList.get(i).getType().equals(timer2.custOrder().get(j).getType())) {
						timer2.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer3.itemIsInside(px, py)) {
				for(int j = 0; j < timer3.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals("CoffeeFull") && timer3.custOrder().get(j).getType().equals("CoffeeOrder") && hitBox.getType().equals("CoffeeFull")) {
						if(i == 0 && coffee1Selected) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
							coffee1Selected = false;
						}else if(i == 1 && coffee2Selected) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
							coffee2Selected = false;
						}else if(i == 2 && coffee3Selected) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
							coffee3Selected = false;
						}
						break;
					}
					if(objectList.get(i).getType().equals(timer3.custOrder().get(j).getType())) {
						timer3.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(objectList.get(i).getType().equals("CoffeeFull")) {
				if(i == 0) {
					objectList.get(i).setPosition(195, 485);
				}
				if(i == 1) {
					objectList.get(i).setPosition(255, 490);
				}
				if(i == 2) {
					objectList.get(i).setPosition(315, 495);
				}
			}else if(objectList.get(i).getType().equals("CoffeeEmpty")) {
				if(i == 0 && objectList.get(i).getX() != 195 && objectList.get(i).getY() != 485) {
					objectList.get(i).setPosition(195, 485);
				}
				if(i == 1 && objectList.get(i).getX() != 255 && objectList.get(i).getY() != 490) {
					objectList.get(i).setPosition(255, 490);
				}
				if(i == 2 && objectList.get(i).getX() != 315 && objectList.get(i).getY() != 495) {
					objectList.get(i).setPosition(315, 495);
				}
			}else if (objectList.get(i).isCake() && objectList.get(i).getBounds().contains(mp) && !hitBox.getType().equals("Blueberry") && !hitBox.getType().equals("Strawberry")) {
				if(px>=500 && px<= 780 && py >= 390 && py <= 540 && (!spot1 || !spot2 || !spot3 || !spot4)) {
					if(!spot1) {
						objectList.get(i).setPosition(515, 370);
						spot1 = true;
						objectList.get(i).setInside(true, 515, 370);
					}else if(!spot2) {
						objectList.get(i).setPosition(505, 440);
						spot2 = true;
						objectList.get(i).setInside(true, 505, 440);
					}else if(!spot3) {
						objectList.get(i).setPosition(641, 370);
						spot3 = true;
						objectList.get(i).setInside(true, 641, 370);
					}else if(!spot4) {
						objectList.get(i).setPosition(645, 440);
						spot4 = true;
						objectList.get(i).setInside(true, 645, 440);
					}
				}else if(px>=915 && px<= 1130 && py >= 375 && py <= 700 && (!oven1||!oven2||!oven3||!oven4)) {
					if(!oven1) {
						objectList.get(i).setPosition(970, 375);
						objectList.get(i).ovenChange(1);
						oven1 = true;
						new OvenTimer(10, objectList.get(i));
						System.out.println("Oven Starting: Done in 10 seconds");
						objectList.get(i).setInsideOven(true, 970, 375);
					}else if(!oven2) {
						objectList.get(i).setPosition(950, 452);
						objectList.get(i).ovenChange(2);
						oven2 = true;
						new OvenTimer(10, objectList.get(i));
						System.out.println("Oven Starting: Done in 10 seconds");
						objectList.get(i).setInsideOven(true, 950, 452);
					}else if(!oven3) {
						objectList.get(i).setPosition(933, 530);
						objectList.get(i).ovenChange(3);
						oven3 = true;
						new OvenTimer(10, objectList.get(i));
						System.out.println("Oven Starting: Done in 10 seconds");
						objectList.get(i).setInsideOven(true, 933, 530);
					}else if(!oven4) {
						objectList.get(i).setPosition(920, 609);
						objectList.get(i).ovenChange(4);
						oven4 = true;
						new OvenTimer(10, objectList.get(i));
						System.out.println("Oven Starting: Done in 10 seconds");
						objectList.get(i).setInsideOven(true, 920, 609);
					}
				}else if(px>=60 && px<= 190 && py >= 600 && py <= 720) {
					objectList.remove(i);
				}else if(objectList.get(i).isInside()) {
					objectList.get(i).setPosition(objectList.get(i).getInsideX(), objectList.get(i).getInsideY());
				}else if(objectList.get(i).isInsideOven()) {
					objectList.get(i).setPosition(objectList.get(i).getInsideOvenX(), objectList.get(i).getInsideOvenY());
				}else {
					objectList.remove(i);
				}
			}
		}
		hitBox = null;
	}

	//@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	//@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
			//increment score depending on key code for forward and back
			//will also move chicken forward or back
		
			
	}

	//@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	//@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(hitBox == null) {
			return;
		}
		if (hitBox != null) {
			Point mp = e.getPoint();
			Rectangle bounds = hitBox.getBounds();
			bounds.x = mp.x - offset.x;
			bounds.y = mp.y - offset.y;
			for(Object box: objectList) {
				if(box.equals(hitBox) && box.getDrag()) {
					box.setPosition(bounds.x, bounds.y);
				}
			}
		}
	}

}
