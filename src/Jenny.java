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
		
		coffee1.paint(g);
		coffee2.paint(g);
		coffee3.paint(g);
		for(Object obj: objectList) {
			obj.paint(g);
		}
		
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
					g.fillRect(10, 350, 140, 70); //coffee
					g.fillRect(360, 555, 140, 70); //cake batter
					g.fillRect(1150, 350, 120, 90); //bake cake
					g.fillRect(200, 620, 110, 70); // trash
					g.fillRect(400, 240, 220, 130); // customers
					g.fillRect(300, 15, 180, 90); //lost customer count
					
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
				g.setColor(Color.PINK);
				g.fillRect(0, 0, 1280, 750);
				
				g.setColor(Color.black);
				g.setFont(new Font("Serif", Font.PLAIN, 40));
				g.drawString("Press Space to Play Again", 460, 370);
				if(count == 3) {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Serif", Font.PLAIN, 100));
					g.drawString("Nice Try: Game Over", 430, 300);
				}else {
					g.setColor(Color.WHITE);
					g.setFont(new Font("Serif", Font.PLAIN, 100));
					g.drawString("Success! Earned " + total + " coins!", 430, 300);
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
