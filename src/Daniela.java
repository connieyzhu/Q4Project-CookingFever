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
	Background cafeCounter = new Background(0, 0, "/imgs/CafeCounterv2.png");
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
		for(int i = 2; i< objectList.size(); i++) {
			if(i==oven1Index) {
				if(objectList.get(i).getX() != 970 && objectList.get(i).getY() != 375) {
					oven1= false;
				}else {
					oven1 = true; 
				}
			}else if(i==oven1Index) {
				if(objectList.get(i).getX() != 950 && objectList.get(i).getY() != 452) {
					oven2= false;
				}else {
					oven2 = true; 
				}
			}else if(i==oven1Index) {
				if(objectList.get(i).getX() != 933 && objectList.get(i).getY() !=530) {
					oven3= false;
				}else {
					oven3 = true; 
				}
			}else if(i==oven1Index) {
				if(objectList.get(i).getX() != 920 && objectList.get(i).getY() != 609) {
					oven4= false;
				}else {
					oven4 = true; 
				}
			}
		}	
	}
	
	public void checkSpots() {
		for(Object obj: objectList) {
			if(obj.getX() != 485 && obj.getY() != 370) {
				spot1 = false;
				//System.out.println("alse");
			}else {
				spot1 = true;
				break;
			}
		}
		for(Object obj: objectList) {
			if(obj.getX() != 475 && obj.getY() != 440) {
				spot2 = false;
			}else {
				spot2 = true;
				break;
			}
		}
		for(Object obj: objectList) {
			if(obj.getX() != 605 && obj.getY() != 370) {
				spot3 = false;
			}else {
				spot3 = true;
				break;
			}
		}
		for(Object obj: objectList) {
			if(obj.getX() != 615 && obj.getY() != 440) {
				spot4 = false;
				//System.out.println("alse");
			}else {
				spot4 = true;
				break;
			}
		}
	}
	
	public void fruitChange() {
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
		public void mouseClicked(MouseEvent arg0) {
		
		if(timer.getCoin().getCollect()) {
			int x = timer.getCoin().getX();
			int y = timer.getCoin().getY();
			int numCollect = 0;
			while(numCollect < 1) {
				if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
					System.out.println("collect");
					timer.getCoin().setCollect(false);
					for(int i = 0; i < timer.orderDoneArr().length; i++) {
						if(timer.orderDoneArr()[i]) {
							if(timer.custOrder()[i].getType().equals("CoffeeOrder")) {
								total += 4;
							}else {
								total += 15;
							}
						}
					}
					numCollect++;
				}
			}
		}
		
		if(timer1.getCoin().getCollect()) {
			int x = timer1.getCoin().getX();
			int y = timer1.getCoin().getY();
			int numCollect = 0;
			while(numCollect < 1) {
				if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
					System.out.println("collect");
					timer1.getCoin().setCollect(false);
					for(int i = 0; i < timer1.orderDoneArr().length; i++) {
						if(timer1.orderDoneArr()[i]) {
							if(timer1.custOrder()[i].getType().equals("CoffeeOrder")) {
								total += 4;
							}else {
								total += 15;
							}
						}
					}
					numCollect++;
				}
			}
		}
		
		if(timer2.getCoin().getCollect()) {
			int x = timer2.getCoin().getX();
			int y = timer2.getCoin().getY();
			int numCollect = 0;
			while(numCollect < 1) {
				if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
					System.out.println("collect");
					timer2.getCoin().setCollect(false);
					for(int i = 0; i < timer2.orderDoneArr().length; i++) {
						if(timer2.orderDoneArr()[i]) {
							if(timer2.custOrder()[i].getType().equals("CoffeeOrder")) {
								total += 4;
							}else {
								total += 15;
							}
						}
					}
					numCollect++;
				}
			}
		}
		
		if(timer3.getCoin().getCollect()) {
			int x = timer3.getCoin().getX();
			int y = timer3.getCoin().getY();
			int numCollect = 0;
			while(numCollect < 1) {
				if(arg0.getX() >= x && arg0.getX() <= x+50 && arg0.getY() >= y && arg0.getY() <= y+80) {
					System.out.println("collect");
					timer3.getCoin().setCollect(false);
					for(int i = 0; i < timer3.orderDoneArr().length; i++) {
						if(timer3.orderDoneArr()[i]) {
							if(timer3.custOrder()[i].getType().equals("CoffeeOrder")) {
								total += 4;
							}else {
								total += 15;
							}
						}
					}
					numCollect++;
				}
			}
		}
		
//		for(int i = 3; i < objectList.size(); i++) {
//			if(objectList.get(i).getType().equals("VanStrawBakeOven")){
//				if(arg0.getX()==objectList.get(i).getX() && arg0.getY()==objectList.get(i).getY()) {
//					objectList.get(i).fullBakeChange(); 
//				}
//			}
//			
//			if(objectList.get(i).getType().equals("VanBlueBakeOven")){
//				if(arg0.getX()==objectList.get(i).getX() && arg0.getY()==objectList.get(i).getY()) {
//					objectList.get(i).fullBakeChange(); 
//				}
//			}
//			
//			if(objectList.get(i).getType().equals("ChocStrawBakeOven")){
//				if(arg0.getX()==objectList.get(i).getX() && arg0.getY()==objectList.get(i).getY()) {
//					objectList.get(i).fullBakeChange(); 
//				}
//			}
//			
//			if(objectList.get(i).getType().equals("ChocBlueBakeOven")){
//				if(arg0.getX()==objectList.get(i).getX() && arg0.getY()==objectList.get(i).getY()) {
//					objectList.get(i).fullBakeChange(); 
//				}
//			}
//		}
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

	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		fruitChange();
		hitBox = null;
		Point mp = arg0.getPoint();
		int px = arg0.getX();
		int py = arg0.getY();
		for(int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).getBounds().contains(mp)) {
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
						System.out.println("spot3 filled");
						objectList.get(i).setPosition(641, 370);
						spot3 = true;
						spot3Index = i;
						objectList.get(i).setInside(true, 641, 370);
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
