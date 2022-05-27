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
	
	Object coffee1 = new Object(195, 485, "CoffeeEmpty", 1.0);
	Object coffee2 = new Object(255, 490, "CoffeeEmpty", 1.0);
	Object coffee3 = new Object(315, 495, "CoffeeEmpty", 1.0);
	
	//Person: (100, 130), (390, 130), (680,130), 970, 130)
	//Order Form: (20, 70), (310, 70), (600, 70), 890, 70)
	//Timer: (110, 75), (400, 75), (690, 75), (980, 75)
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;

	private boolean b; 
	int total = 0;
	 
	public void paint(Graphics g) {
		cafeBg.paint(g);
		cafeCounter.paint(g);
		timer.paint(g);
		timer1.paint(g);
		timer2.paint(g);
		timer3.paint(g);
		
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
		setOvenDrag();
		g.drawRect(60, 600, 130, 120);
		
		g.drawRect(10, 50, 280, 280);
		g.drawRect(300, 50, 280, 280);
		g.drawRect(590, 50, 280, 280);
		g.drawRect(880, 50, 280, 280);
		
		g.drawRect(170, 360, 210, 200);
		
		//scoring
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString(total + "", 600, 47);
		
		
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
				if(objectList.get(i).getX() == 515 && objectList.get(i).getY() == 370) {
					spot1 = true;
				}else {
					spot1 = false;
				}
			}else if(i == spot2Index) {
				if(objectList.get(i).getX() == 505 && objectList.get(i).getY() == 440) {
					spot2 = true;
				}else {
					spot2 = false;
				}
			}else if(i == spot3Index) {
				if(objectList.get(i).getX() == 641 && objectList.get(i).getY() == 370) {
					spot3 = true;
				}else {
					spot3 = false;
				}
			}else if(i == spot4Index) {
				if(objectList.get(i).getX() == 645 && objectList.get(i).getY() == 440) {
					spot4 = true;
				}else {
					spot4 = false;
				}
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
		if(arg0.getX() >= 170 && arg0.getX() <= 170+210 && arg0.getY() >= 360 && arg0.getY() <= 360 + 200) {
			new CoffeeTimer(5);
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
		//System.out.println("MouseLocation: " + px + " " + py);
		for(int i = 0; i < objectList.size(); i++) {
			if(hitBox.getType().equals("Blueberry") || hitBox.getType().equals("Strawberry")) {
				for(Object batter:objectList) {
					if(batter.getType().equals("VanBatter")||batter.getType().equals("ChocBatter")){
						if(batter.getBounds().contains(mp)) {
							if(hitBox.getType().equals("Blueberry")) {
								batter.addFruitChange("Blueberry");
								System.out.println("changed");
							}else if(hitBox.getType().equals("Strawberry")) {
								batter.addFruitChange("Strawberry");
							}
						}
					}
				}
				objectList.remove(hitBox.getIndex());
			}
			if(timer.itemIsInside(px, py)) {
				for(int j = 0; j < timer.custOrder().size(); j++) {
					if(objectList.get(i).getType().equals("CoffeeFull") && timer.custOrder().get(j).getType().equals("CoffeeOrder")) {
						if(i == 0) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
						}else if(i == 1) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
						}else if(i == 2) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer.custOrder().remove(j);
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
					if(objectList.get(i).getType().equals("CoffeeFull") && timer1.custOrder().get(j).getType().equals("CoffeeOrder")) {
						if(i == 0) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
						}else if(i == 1) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
						}else if(i == 2) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer1.custOrder().remove(j);
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
					if(objectList.get(i).getType().equals("CoffeeFull") && timer2.custOrder().get(j).getType().equals("CoffeeOrder")) {
						if(i == 0) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
						}else if(i == 1) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
						}else if(i == 2) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer2.custOrder().remove(j);
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
					if(objectList.get(i).getType().equals("CoffeeFull") && timer3.custOrder().get(j).getType().equals("CoffeeOrder")) {
						if(i == 0) {
							objectList.remove(0);
							objectList.add(0, new Object(195, 485, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
						}else if(i == 1) {
							objectList.remove(1);
							objectList.add(1, new Object(255, 490, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
						}else if(i == 2) {
							objectList.remove(2);
							objectList.add(2, new Object(315, 495, "CoffeeEmpty", 1.0));
							timer3.custOrder().remove(j);
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
						spot1Index = i;
						objectList.get(i).setInside(true, 515, 370);
						System.out.println("spot1 filled");
					}else if(!spot2) {
						objectList.get(i).setPosition(505, 440);
						spot2 = true;
						spot2Index = i;
						objectList.get(i).setInside(true, 505, 440);
						System.out.println("spot2 filled");
					}else if(!spot3) {
						objectList.get(i).setPosition(641, 370);
						spot3 = true;
						spot3Index = i;
						objectList.get(i).setInside(true, 641, 370);
						System.out.println("spot3 filled");
					}else if(!spot4) {
						objectList.get(i).setPosition(645, 440);
						spot4 = true;
						spot4Index = i;
						objectList.get(i).setInside(true, 645, 440);
						System.out.println("spot4 filled");
					}
				}else if(px>=915 && px<= 1130 && py >= 375 && py <= 700 && (!oven1||!oven2||!oven3||!oven4)) {
					if(!oven1) {
						objectList.get(i).setPosition(970, 375);
						objectList.get(i).ovenChange();
						oven1 = true;
						oven1Index = i;
						objectList.get(i).setInside(true, 970, 375);
					}else if(!oven2) {
						objectList.get(i).setPosition(950, 452);
						objectList.get(i).ovenChange();
						oven2 = true;
						oven2Index = i; 
						objectList.get(i).setInside(true, 950, 452);
					}else if(!oven3) {
						objectList.get(i).setPosition(933, 530);
						objectList.get(i).ovenChange();
						oven3 = true;
						oven3Index = i; 
						objectList.get(i).setInside(true, 933, 530);
					}else if(!oven4) {
						objectList.get(i).setPosition(920, 609);
						objectList.get(i).ovenChange();
						oven4 = true;
						oven4Index = i; 
						objectList.get(i).setInside(true, 920, 609);
					}
					if(oven1) {
						new OvenTimer(5, oven1Index);
					}
					if(oven2) {
						new OvenTimer(5, oven2Index);
					}
					if(oven3) {
						new OvenTimer(5, oven3Index);
					}
					if(oven4) {
						new OvenTimer(5, oven4Index);
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
			if(mp.getX() >= 500 && mp.getX() <= 780 && mp.getY() >= 390 && mp.getY() <= 540) {
				inside = true;
			}else {
				inside = false;
			}
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
	private Rectangle rectangle;
	private boolean insideSquares;
	private int insideX, insideY;
	private double scale;
	private boolean draggable = true;
	

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
		if(type.equals("CoffeeEmpty")) {
			img = getImage("/imgs/CoffeeEmpty.png");
			rectangle = new Rectangle(x, y, 58, 60);
		}
		if(type.equals("CoffeeFull")) {
			img = getImage("/imgs/CoffeeFull.png");
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
		type = "CoffeeFull";
	}
	
	public void ovenChange() {
		if(type.equals("VanStrawBatter")) {
			changePicture("/imgs/VanStrawOven.png");
			type = "VanStrawOven";
		}
		
		if(type.equals("VanBlueBatter")) {
			changePicture("/imgs/VanBlueOven.png");
			type = "VanBlueOven";
		}
		
		if(type.equals("ChocStrawBatter")) {
			changePicture("/imgs/ChocStrawOven.png");
			type = "ChocStrawOven";
		}
		
		if(type.equals("ChocBlueBatter")) {
			changePicture("/imgs/ChocBlueOven.png");
			type = "ChocBlueOven";
		}
	}
	
	public void bakeChange() {
		if(type.equals("VanStrawOven")) {
			changePicture("/imgs/VanStrawBakeOven.png");
			type = "VanStrawBakeOven"; 
		}
		
		if(type.equals("VanBlueOven")) {
			changePicture("/imgs/VanBlueBakeOven.png");
			type = "VanBlueBakeOven";
		}
		
		if(type.equals("ChocStrawOven")) {
			changePicture("/imgs/ChocStrawBakeOven.png");
			type = "ChocStrawBakeOven";
		}
		
		if(type.equals("ChocBlueOven")) {
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
			type = "VanBlueBakeOven";
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
	
	public boolean isCake() {
		if(type.equals("ChocBatter")||type.equals("VanBatter")||type.equals("ChocBlueBatter")||type.equals("ChocStrawBatter")
				||type.equals("VanBlueBatter")||type.equals("VanStrawBatter")||type.equals("ChocBlueBake")||type.equals("ChocStrawBake")
				||type.equals("VanBlueBake")||type.equals("VanStrawBake")) {
			return true;
		}
		return false;
	}
	
	public boolean isOven() {
		if(type.equals("ChocBlueOven")||type.equals("ChocStrawOven")||type.equals("VanBlueOven")||type.equals("VanStrawOven")) {
			return true;
		}
		return false;
	}
	
	public int getIndex() {
		for(int i = 0; i < Runner.getObjectList().size(); i++) {
			if(type.equals(Runner.getObjectList().get(i).getType()) && x == Runner.getObjectList().get(i).getX()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean getDrag() {
		return draggable;
	}
	
	public void setDrag(boolean b) {
		draggable = b;
	}
}
