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

public class Runner extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
	
	private static ArrayList<Object> objectList;
	private Object hitBox;
    private Point offset;
    
    private boolean spot1 = false;
    private boolean spot2 = false;
    private boolean spot3 = false;
    private boolean spot4 = false;
    private boolean inside = false;
    
    private int spot1Index;
    private int spot2Index;
    private int spot3Index;
    private int spot4Index;
    
    private boolean oven1 = false;
    private boolean oven2 = false;
    private boolean oven3 = false;
    private boolean oven4 = false;
    
    private int oven1Index;
    private int oven2Index;
    private int oven3Index;
    private int oven4Index;
	
	Background cafeBg = new Background(0, 0, "/imgs/CafeBG.png");
	Background cafeCounter = new Background(0, 0, "/imgs/CafeCounter.png");
	Position pos = new Position("timer");
	
	Position posWait = new Position("wait");
	
	OrderTimer timer = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer1 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer2 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	OrderTimer timer3 = new OrderTimer(pos.getX(), 75, 1, posWait.getWait());
	
	Object coffee1 = new Object(195, 485, "Coffee", 1.0);
	Object coffee2 = new Object(255, 490, "Coffee", 1.0);
	Object coffee3 = new Object(315, 495, "Coffee", 1.0);
	
	Music music = new Music();
	
	//Person: (100, 130), (390, 130), (680,130), 970, 130)
	//Order Form: (20, 70), (310, 70), (600, 70), 890, 70)
	//Timer: (110, 75), (400, 75), (690, 75), (980, 75)
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	private boolean b;
	private boolean restart = true; 
	private boolean playAgain = false;
	private boolean tutorial = false;
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
		
		g.drawRect(505, 620, 130, 85);
		g.drawRect(645, 620, 130, 85);
		g.drawRect(550, 550, 80, 50);
		g.drawRect(660, 550, 80, 50);
		
		//Holder
		g.drawRect(500, 390, 280, 150);
		g.drawRect(500, 390, 140, 70);
		g.drawRect(500, 460, 140, 80);
		g.drawRect(640, 390, 140, 70);
		g.drawRect(640, 460, 140, 80);
		
		checkSpots();
		
		g.drawRect(940, 375, 190, 75);
		g.drawRect(940, 455, 190, 75);
		g.drawRect(915, 535, 190, 75);
		g.drawRect(915, 615, 190, 75);
		
		checkOven(); 
		g.drawRect(60, 600, 130, 120);
		
		g.drawRect(10, 50, 280, 280);
		g.drawRect(300, 50, 280, 280);
		g.drawRect(590, 50, 280, 280);
		g.drawRect(880, 50, 280, 280);
		
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
					g.fillRect(10, 350, 110, 70); //coffee
					g.fillRect(360, 555, 115, 70); //cake batter
					g.fillRect(1150, 350, 100, 90); //bake cake
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
					
					g.drawString("2: Coffee ", 20, 370);
					g.drawString("click on machine", 20, 390);
					g.drawString("wait 5 seconds", 20, 410);
					
					g.drawString("3: Cake Batter ", 365, 575);
					g.drawString("drag batter & fruit", 365, 595);
					g.drawString("onto tray", 365, 615);
					
					g.drawString("4: Bake Cake ", 1155, 370);
					g.drawString("drag batter to ", 1155, 390);
					g.drawString("available oven ", 1155, 410);
					g.drawString("wait 5 seconds", 1155, 430);
					
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
					
			}
			else if(playAgain) {
				for(int i = 0; i < objectList.size(); i++) {
					objectList.remove(i);
				}
				objectList.add(coffee1);
				objectList.add(coffee2);
				objectList.add(coffee3);
			
				System.out.println("list size " + objectList.size());
				
				g.setColor(Color.PINK);
				g.fillRect(0, 0, 1280, 750);
				
				g.setColor(Color.black);
				g.setFont(new Font("Serif", Font.PLAIN, 40));
				g.drawString("Press Space to Play Again", 460, 370);
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
		//f.setBackground();
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
	
	
	public Rectangle coffeeGetRect() {
		return new Rectangle(170, 365, 220, 210);
	}
	
	public void checkOven() {
		for(int i = 3; i< objectList.size(); i++) {
			if(i==oven1Index) {
				if(objectList.get(i).getX() != 970 && objectList.get(i).getY() != 375) {
					oven1= false;
				}else {
					oven1 = true; 
				}
			}else if(i==oven2Index) {
				if(objectList.get(i).getX() != 950 && objectList.get(i).getY() != 452) {
					oven2= false;
				}else {
					oven2 = true; 
				}
			}else if(i==oven3Index) {
				if(objectList.get(i).getX() != 933 && objectList.get(i).getY() !=530) {
					oven3= false;
				}else {
					oven3 = true; 
				}
			}else if(i==oven4Index) {
				if(objectList.get(i).getX() != 920 && objectList.get(i).getY() != 609) {
					oven4= false;
				}else {
					oven4 = true; 
				}
			}
		}	
	}
	
	
	public void checkSpots() {
		for(int i = 0; i < objectList.size(); i++) {
			if(i == spot1Index) {
				if(objectList.get(i).getX() != 515 && objectList.get(i).getY() != 370) {
					spot1 = false;
				}else {
					spot1 = true;
				}
			}else if(i == spot2Index) {
				if(objectList.get(i).getX() != 505 && objectList.get(i).getY() != 440) {
					spot2 = false;
				}else {
					spot2 = true;
				}
			}else if(i == spot3Index) {
				if(objectList.get(i).getX() != 641 && objectList.get(i).getY() != 370) {
					spot3 = false;
				}else {
					spot3 = true;
				}
			}else if(i == spot4Index) {
				if(objectList.get(i).getX() != 645 && objectList.get(i).getY() != 440) {
					spot4 = false;
				}else {
					spot4 = true;
				}
			}
		}
	}
	
	public void fruitChange() {
		if(hitBox == null) return;
		for(int i = 0; i < objectList.size(); i++) {
			if(hitBox.equals(objectList.get(i)) && (objectList.get(i).getType().equals("Blueberry") || objectList.get(i).getType().equals("Strawberry"))) {
				for(Object batter:objectList) {
					if(objectList.get(i).getX() >= batter.getX() && objectList.get(i).getX() <= batter.getX() + batter.getBounds().getWidth() && 
							objectList.get(i).getY() >= batter.getY() && objectList.get(i).getY() <= batter.getY() + batter.getBounds().getHeight()) {
						if(objectList.get(i).getType().equals("Blueberry")) {
							if(batter.getType().equals("ChocBatter")) {
								batter.addFruitChange("Blueberry");
							}else if(batter.getType().equals("VanBatter")) {
								batter.addFruitChange("Blueberry");
							}
						}else if(objectList.get(i).getType().equals("Strawberry")) {
							if(batter.getType().equals("ChocBatter")) {
								batter.addFruitChange("Strawberry");
							}else if(batter.getType().equals("VanBatter")) {
								batter.addFruitChange("Strawberry");
							}
						}
					}
				}
				objectList.remove(i);
			}
		}
	}
	
	public static ArrayList<Object> getObjectList(){
		return objectList;
	}
	
	public static Object getObject(int index) {
		return objectList.get(index);
	}
	
	//@Override
public void mouseClicked(MouseEvent arg0) {
		//click start button - g.fillRect(1165, 20, 100, 40);
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
				//	g.fillRect(1165, 80, 100, 40);
				tutorial = true;
			}
		}
	
		if(timer.getCoin().getCollect()) {
			int x = timer.getCoin().getX();
			int y = timer.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				System.out.println("collect");
				timer.getCoin().setCollect(false);
				total += timer.getTotal();
			}
		}
		
		if(timer1.getCoin().getCollect()) {
			int x = timer1.getCoin().getX();
			int y = timer1.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				System.out.println("collect");
				timer1.getCoin().setCollect(false);
				total += timer1.getTotal();
			}
		}
		
		if(timer2.getCoin().getCollect()) {
			int x = timer2.getCoin().getX();
			int y = timer2.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				System.out.println("collect");
				timer2.getCoin().setCollect(false);
				total += timer2.getTotal();
			}
		}
		
		if(timer3.getCoin().getCollect()) {
			int x = timer3.getCoin().getX();
			int y = timer3.getCoin().getY();
			if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
				System.out.println("collect");
				timer3.getCoin().setCollect(false);
				total += timer3.getTotal();
			}
		}
		new CoffeeTimer(5);
	
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
			if (box.getBounds().contains(mp)) {
				hitBox = box;
				offset = new Point();
				offset.x = mp.x - box.getBounds().x;
				offset.y = mp.y - box.getBounds().y;
				System.out.println("press");
			}
		}
		
		for(int i = 3; i < objectList.size(); i++) {
			if(arg0.getX()>=915 && arg0.getX()<=1130 && arg0.getY()>=375 && arg0.getY()<=700){
				if(objectList.get(i).getType().equals("VanStrawBakeOven")) {
					System.out.println("ya");
					objectList.get(i).fullBakeChange();
				}
				if(objectList.get(i).getType().equals("VanBlueBakeOven")) {
					System.out.println("ya");
					objectList.get(i).fullBakeChange(); 
				}
				
				if(objectList.get(i).getType().equals("ChocStrawBakeOven")) {
					System.out.println("ya");
					objectList.get(i).fullBakeChange(); 
				}
				
				if(objectList.get(i).getType().equals("ChocBlueBakeOven")) {
					System.out.println("ya");
					objectList.get(i).fullBakeChange(); 
				}
			}
		}

	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		fruitChange();
		hitBox = null;
		Point mp = arg0.getPoint();
		int px = arg0.getX();
		int py = arg0.getY();
		for(int i = 3; i < objectList.size(); i++) {
			if(timer.itemIsInside(px, py)) {
				System.out.println("intimer");
				for(int j = 0; j < timer.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals(timer.custOrder().get(j).getType())) {
						timer.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer1.itemIsInside(px, py)) {
				System.out.println("intimer");
				for(int j = 0; j < timer1.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals(timer1.custOrder().get(j).getType())) {
						timer1.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer2.itemIsInside(px, py)) {
				System.out.println("intimer");
				for(int j = 0; j < timer2.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals(timer2.custOrder().get(j).getType())) {
						timer2.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if(timer3.itemIsInside(px, py)) {
				System.out.println("intimer");
				for(int j = 0; j < timer3.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals(timer3.custOrder().get(j).getType())) {
						timer3.custOrder().remove(j);
						objectList.remove(i);
						break;
					}
				}
			}else if (objectList.get(i).getBounds().contains(mp)) {
				if(px>=500 && px<= 780 && py >= 390 && py <= 540 && (!spot1 || !spot2 || !spot3 || !spot4)) {
					System.out.println("mous " + px + " " + py);
					if(!spot1) {
						objectList.get(i).setPosition(515, 370);
						spot1 = true;
						spot1Index = i;
						objectList.get(i).setInside(true, 515, 370);
						System.out.println("spot1 filled");
					}else if(!spot3) {
						System.out.println("spot3 filled");
						objectList.get(i).setPosition(641, 370);
						spot3 = true;
						spot3Index = i;
						objectList.get(i).setInside(true, 641, 370);
					}else if(!spot2) {
						objectList.get(i).setPosition(505, 440);
						spot2 = true;
						spot2Index = i;
						objectList.get(i).setInside(true, 505, 440);
						System.out.println("spot2 filled");
					}else if(!spot4) {
						objectList.get(i).setPosition(645, 440);
						spot4 = true;
						spot4Index = i;
						objectList.get(i).setInside(true, 645, 440);
						System.out.println("spot4 filled");
					} 
				}else if(px>=915 && px<= 1130 && py >= 375 && py <= 700 && (!oven1||!oven2||!oven3||!oven4)) {
					System.out.println("gotoven");
					if(!oven1) {
						System.out.println("oven1");
						objectList.get(i).setPosition(970, 375);
						objectList.get(i).ovenChange();
						oven1 = true;
						oven1Index = i;
						objectList.get(i).setInside(true, 970, 375);
						new OvenTimer(5);
					}else if(!oven2) {
						System.out.println("oven2");
						objectList.get(i).ovenChange();
						oven2 = true;
						oven2Index = i; 
						objectList.get(i).setPosition(950, 452);
						objectList.get(i).setInside(true, 950, 452);
						new OvenTimer(5);
					}else if(!oven3) {
						System.out.println("oven3");
						objectList.get(i).ovenChange();
						oven3 = true;
						oven3Index = i;  
						objectList.get(i).setPosition(933, 530);
						objectList.get(i).setInside(true, 933, 530);
						new OvenTimer(5);
					}else if(!oven4) {
						System.out.println("oven4");
						objectList.get(i).ovenChange();
						oven4 = true;
						oven4Index = i; 
						objectList.get(i).setPosition(920, 609);
						objectList.get(i).setInside(true, 920, 609);
						new OvenTimer(5);
					}
				}else if(px>=60 && px<= 190 && py >= 600 && py <= 720) {
					objectList.remove(i);
				}else if(objectList.get(i).isInside()) {
					objectList.get(i).setPosition(objectList.get(i).getInsideX(), objectList.get(i).getInsideY());
				}else {
					objectList.remove(i);
				}
			}
		}
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
		if(arg0.getKeyCode() == 32 && playAgain) {
			playAgain = false;
			restart = true;
		}
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
		if (hitBox != null) {
			Point mp = e.getPoint();
			if(mp.getX() >= 500 && mp.getX() <= 780 && mp.getY() >= 390 && mp.getY() <= 540) {
				inside = true;
			}else {
				inside = false;
			}
			Rectangle bounds = hitBox.getBounds();
			bounds.x = mp.x - offset.x;
			bounds.y = mp.y - offset.y;
			for(Object box: objectList) {
				if(box.equals(hitBox)) {
					box.setPosition(bounds.x, bounds.y);
				}
			}
		}
	}

}

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

public class Object{
	private int x,y; 
	private Image img;
	private String type;
	private AffineTransform tx;
	boolean filled = false; 
	private Rectangle rectangle;
	private boolean insideSquares;
	private int insideX, insideY;
	private double scale;
	

	public Object(int x, int y, String s, double setScale){
		this.x = x;
		this.y = y; 
		type = s;
		scale = setScale;
		if(type.equals("Blueberry")) {
			img = getImage("/imgs/Blueberry.png");
			rectangle = new Rectangle(x, y, 100, 55);
		}
		if(type.equals("ChocBlueBake")) {
			img = getImage("/imgs/ChocBlueBake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocBlueBatter")) {
			img = getImage("/imgs/ChocBlueUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocStrawBake")) {
			img = getImage("/imgs/ChocStrawBake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocStrawBatter")) {
			img = getImage("/imgs/ChocStrawUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocBatter")) {
			img = getImage("/imgs/ChocUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("Coffee")) {
			img = getImage("/imgs/CoffeeEmpty.png");
			rectangle = new Rectangle(x, y, 58, 60);
		}
		if(type.equals("Strawberry")) {
			img = getImage("/imgs/Strawberry.png");
			rectangle = new Rectangle(x, y, 105, 60);
		}
		if(type.equals("VanBlueBake")) {
			img = getImage("/imgs/VanBlueBake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanBlueBatter")) {
			img = getImage("/imgs/VanBlueUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanStrawBake")) {
			img = getImage("/imgs/VanStrawBake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanStrawBatter")) {
			img = getImage("/imgs/VanStrawUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanBatter")) {
			img = getImage("/imgs/VanUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("CoffeeOrder")) {
			img = getImage("/imgs/CoffeeFull.png");
			rectangle = new Rectangle(x + 32, y, 125, 95);
		}
		
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
		tx.scale(scale, scale); //previously 1.0
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scale, scale); //previously 0.04
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
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
		
	}
	
	public void change() {
		changePicture("/imgs/CoffeeFull.png");
		filled = true; 
	}
	
	public void ovenChange() {
		if(type.equals("VanStrawBatter")) {
			changePicture("/imgs/VanStrawOven.png");
			type = "VanStrawBake";
		}
		
		if(type.equals("VanBlueBatter")) {
			changePicture("/imgs/VanBlueOven.png");
			type = "VanBlueBake";
		}
		
		if(type.equals("ChocStrawBatter")) {
			changePicture("/imgs/ChocStrawOven.png");
			type = "ChocStrawBake";
		}
		
		if(type.equals("ChocBlueBatter")) {
			changePicture("/imgs/ChocBlueOven.png");
			type = "ChocBlueBake";
		}
	}
	
	public void bakeChange() {
		if(type.equals("VanStrawBake")) {
			changePicture("/imgs/VanStrawBakeOven.png");
			type = "VanStrawBakeOven"; 
		}
		
		if(type.equals("VanBlueBake")) {
			changePicture("/imgs/VanBlueBakeOven.png");
			type = "VanBlueBakeOven";
		}
		
		if(type.equals("ChocStrawBake")) {
			changePicture("/imgs/ChocStrawBakeOven.png");
			type = "ChocStrawBakeOven";
		}
		
		if(type.equals("ChocBlueBake")) {
			changePicture("/imgs/ChocBlueBakeOven.png");
			type = "ChocBlueBakeOven";
		}
	}
	
	public void fullBakeChange() {
		if(type.equals("VanStrawBakeOven")) {
			changePicture("/imgs/VanStrawBake.png");
			type = "VanStrawBake"; 
		}
		
		if(type.equals("VanBlueBakeOven")) {
			changePicture("/imgs/VanBlueBake.png");
			type = "VanBlueBake";
		}
		
		if(type.equals("ChocStrawBakeOven")) {
			changePicture("/imgs/ChocStrawBake.png");
			type = "ChocStrawBake";
		}
		
		if(type.equals("ChocBlueBakeOven")) {
			changePicture("/imgs/ChocBlueBake.png");
			type = "ChocBlueBake";
		}
	}
	
	public void addFruitChange(String fruit) {
		if(type.equals("ChocBatter")) {
			if(fruit.equals("Strawberry")) {
				changePicture("/imgs/ChocStrawUnbake.png");
				type = "ChocStrawBatter";
			}
			if(fruit.equals("Blueberry")) {
				changePicture("/imgs/ChocBlueUnbake.png");
				type = "ChocBlueBatter";
			}
		}
		if(type.equals("VanBatter")) {
			if(fruit.equals("Strawberry")) {
				changePicture("/imgs/VanStrawUnbake.png");
				type = "VanStrawBatter";
			}
			if(fruit.equals("Blueberry")) {
				changePicture("/imgs/VanBlueUnbake.png");
				type = "VanBlueBatter";
			}
		}
	}
	
	public Rectangle getBounds() {
        return rectangle;
    }
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isInside() {
		return insideSquares;
	}
	
	public void setInside(boolean b, int ix, int iy) {
		insideSquares = b;
		insideX = ix;
		insideY = iy;
	}
	
	public int getInsideX() {
		return insideX;
	}
	
	public int getInsideY() {
		return insideY;
	}

	public void setPosition(int x2, int y2) {
		// TODO Auto-generated method stub
		this.x = x2;
		this.y = y2; 
		rectangle.setLocation(x2, y2);
		tx.setToTranslation(x, y);
		tx.scale(scale, scale); //previously 1
	}
	
	public boolean objectExit() {
		//g.drawRect(500, 390, 280, 150);
		if(x < 500 || x > 780 || y < 390 || y > 540) {
			return true;
		}
		return false;
	}
}

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
		System.out.println("Pos " + x1);
		this.x = x; 
		this.y = y;
		vx = 0;
		count = 0;
		numReturns = ThreadLocalRandom.current().nextInt(4, 7);//4, 7
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
import java.util.ArrayList;

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
	ArrayList <Object> orders;
	Object item;
	Coin coin;
	private int totalMoney;
	private int numX1, numX2, size, oldAmt;
	private static boolean timerReset = false;
		
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
			orders = new ArrayList<Object>();
			totalMoney = 0;
			oldAmt = 0;
			size = ThreadLocalRandom.current().nextInt(1, 4);
			
			int itemX = x-75;
			int itemY = y+5;
			for(int i = 0; i < size; i++) {
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
				orders.add(item);
				itemX = x-75;
				itemY += 50;
			}
			
			if(orders.size() == 1) {
				if(orders.get(0).getType().equals("CoffeeOrder")) {
					secondsAmt = 50;
				}
				secondsAmt = 100;
			}
			if(orders.size() == 2) {
				secondsAmt = 150;
			}
			if(orders.size() == 3) {
				secondsAmt = 200;
			}
	    	 
			waiting = new Position("wait");
			name = new Position("name");
			custName = name.getName();
			order = new Background(x-90, 70, "/imgs/Order Bubble.png");
			cust = new Customer(personX, wait, 130, custName);
	    	timer = new Timer();
	    	coin = new Coin(personX+70, 290);
	    	
	    	if(x == 110) {
	    		numX1 = 10;
		    	numX2 = 290;
	    	}else if(x == 400){
	    		numX1 = 300;
		    	numX2 = 580;
	    	}else if(x == 690) {
	    		numX1 = 590;
		    	numX2 = 870;
	    	}else {
	    		numX1 = 880;
		    	numX2 = 1160;
	    	}
		}
	    
	    public void runner() {
	    	if(count < 1) {
	    		timer.schedule(new RemindTask(), sec*secondsAmt);
	    		count++;
	    	}
	    }
	    
	    public void paint(Graphics g) {
			//these are the 2 lines of code needed draw an image on the screen
			Graphics2D g2 = (Graphics2D) g;
			
			if(cust.getReady() && orders.size() != 0 && !timerReset) {
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
				
				for(int i = 0; i < orders.size(); i++) {
					orders.get(i).paint(g2);
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
	    
	    public boolean custDone() {
	    	return cust.getReturnDone();
	    }
	    
	    public void restartCount(int num) {
	    	cust.setCount(num);
	    }
	    
	    public void setCustVx(int num) {
	    	cust.setVx(num);
	    }
	    
	    public void reset(boolean num) {
	    	cust.setReset(num);
	    }
	    
	    public void setReturnDoneCust(boolean x) {
	    	cust.setReturnDone(x);
	    }
	    
	    public static void timerReset(boolean x) {
	    	timerReset = x;
	    }
	    
	    public void setSec() {
	    	if(orders.size() == 1) {
				if(orders.get(0).getType().equals("CoffeeOrder")) {
					secondsAmt = 50;
				}
				secondsAmt = 100;
			}
			if(orders.size() == 2) {
				secondsAmt = 150;
			}
			if(orders.size() == 3) {
				secondsAmt = 200;
			}
	    }
	    
	    public void generateNewOrder() {
	    	orders = new ArrayList <Object>();
	    	int itemX = x-75;
			int itemY = y+5;
			totalMoney = 0;
			size = ThreadLocalRandom.current().nextInt(1, 4);
			for(int i = 0; i < size; i++) {
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
				orders.add(item);
				itemX = x-75;
				itemY += 50;
			}
			setSec();
	    }
	    
	    public Coin getCoin() {
	    	return coin;
	    }
	    
	    public int getTotal() {
	    	return oldAmt;
	    }
	    
	    public int getPersonX() {
	    	return personX;
	    }
	    
	   public boolean itemIsInside(int xVal, int yVal) {
		   System.out.println("location: " + numX1 + " " + numX2);
		   if(xVal >= numX1 && xVal <= numX2 && yVal >= 50 && yVal <= 330) {
	    		System.out.println(xVal + " " + yVal);  
			   return true;
	    	}
	    	System.out.println("itemfalse");
		   return false;
	    }
	   
	    public ArrayList<Object> custOrder() {
	    	return orders;
	    }
	    
	    class RemindTask extends TimerTask {
	        public void run() {
	        	if(orders.size() == 0 || timerReset) {
	        		done(true);
	        		y = ogT;
	        		ty = ogTy;
	        		count = 0;
	        		if(!timerReset) {
	        			coin.setCollect(true);
		        		oldAmt = totalMoney;
	        		}
	        		generateNewOrder();
	        	}else if(y == gy+165) {
	        		done(true);
	        		y = ogT;
	        		ty = ogTy;
	        		count = 0;
	        		Runner.addCount(1);
	        		coin.setCollect(false);
	        		generateNewOrder();
	        	}else {
	    			y += 1;
	    			ty -= 1;
	    			timer.schedule(new RemindTask(), sec*secondsAmt); //400
	    		}
	        }
	    }
	  
}

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.MouseInfo;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Position {
	int num;
	static int count = 0;
	static int posCount = 0;
	int[] arr = new int[4];
	int[] person = {100, 390, 680, 970};
	int[] order = {20, 310, 600, 890};
	int[] timer = {110, 400, 690, 980};
	int[] wait = {-800, -3500, -7500, -11500}; //-1800, -4000, -8000, -12000
	String[] names = {"Daphne", "Linda", "Francis", "Kyle"};
	boolean[] available = {true, true, true, true};
	boolean[] availableWait = {true, true, true, true};
	String[] orderItems = {"ChocBlueBake", "ChocStrawBake", "VanBlueBake",
							"VanStrawBake", "CoffeeOrder"};
	/*String[] orderItems = {"ChocBlueBatter", "ChocStrawBatter", "VanBlueBatter",
			"VanStrawBatter", "CoffeeOrder"};*/
	String item;
	
	
	public Position(String purpose) {
		if(purpose == "Person") {
			for(int i = 0; i < arr.length; i++) {
				arr[i] = person[i];
			}
		}
		if(purpose == "Order") {
			for(int i = 0; i < arr.length; i++) {
				arr[i] = order[i];
			}
		}
		if(purpose == "timer") {
			for(int i = 0; i < arr.length; i++) {
				arr[i] = timer[i];
			}
		}
		if(purpose == "name") {
			getName();
		}
		
		if(purpose == "wait") {
			getWait();
		}
		
		if(purpose == "orderItem") {
			getItem();
		}
	}
	
	public String getName() {
		return names[ThreadLocalRandom.current().nextInt(0, 4)];
	}
	
	public void updateWaitAvail() {
		int count = 0;
		for(int i = 0; i < availableWait.length; i++) {
			if(!availableWait[i]) {
				count++;
			}
		}
		if(count == 4) {
			for(int i = 0; i < availableWait.length; i++) {
				availableWait[i] = true;
			}
		}
		count = 0;
	}
	public void updateXAvail() {
		/*for(int i = 0; i < available.length; i++) {
			available[i] = true;
		}*/
		
		int posCount = 0;
		for(int i = 0; i < available.length; i++) {
			if(!available[i]) {
				posCount++;
			}
		}
		if(posCount == 4) {
			for(int i = 0; i < available.length; i++) {
				available[i] = true;
			}
		}
		posCount = 0;
	}
	
	public int newNum() {
		num = ThreadLocalRandom.current().nextInt(0, 4);
		return num;
	}
	
	public int getWait() {
		int num = 0;
		int i = 0;
		while(i < availableWait.length) {
			if(availableWait[i]) {
				num = i;
				i = availableWait.length;
			}else {
				i++;
			}
		}
		
		availableWait[num] = false;
		return wait[num];
	}
	
	public int getX() {
		newNum();
		//updateXAvail();
		if(!available[num]) {
			newNum();
			getX();
		}
		available[num] = false;
		posCount++;
		
		if(posCount%4 == 0) {
			updateXAvail();
		}
		return arr[num];
	}
	
	public String getItem() {
		item = orderItems[ThreadLocalRandom.current().nextInt(0, 5)];
		return item;
	} 
}


