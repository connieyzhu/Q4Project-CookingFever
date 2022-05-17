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
	
	private ArrayList<Object> objectList;
	private Object hitBox;
    private Point offset;
    
    private boolean spot1 = false;
    private boolean spot2 = false;
    private boolean spot3 = false;
    private boolean spot4 = false;
    private boolean inside = false;
    
    private boolean oven1 = false;
    private boolean oven2 = false;
    private boolean oven3 = false;
    private boolean oven4 = false;
	
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
	
	
	public Rectangle coffeeGetRect() {
		return new Rectangle(170, 365, 220, 210);
	}
	
	public void checkOven() {
		for(Object obj: objectList) {
			if(obj.getX()>=940 && obj.getX()<=1130 && obj.getY()>=375 && obj.getY()<=450) {
				oven1 = true; 
			}else {
				oven1 = false;
				break; 
			}
		}
		
		for(Object obj: objectList) {
			if(obj.getX()>=940 && obj.getX()<=1130 && obj.getY()>=455 && obj.getY()<=530) {
				oven2 = true;
			}else {
				oven2 = false;
				break; 
			}
		}
		
		for(Object obj: objectList) {
			if(obj.getX()>=915 && obj.getX()<=1105 && obj.getY()>=535 && obj.getY()<=710) {
				oven3 = true;
			}else {
				oven3 = false; 
				break; 
			}
		}
		
		for(Object obj: objectList) {
			if(obj.getX()>=915 && obj.getX()<=1105 && obj.getY()>=615 && obj.getY()<=690) {
				oven4 = true;
			}else {
				oven4 = false; 
				break; 
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
	
	//@Override
	public void mouseClicked(MouseEvent arg0) {
	
//		if(arg0.getX()>=170 && arg0.getX()<= 390 && arg0.getY() >= 365 && arg0.getY() <= 600){
//			System.out.print("hi");
//			try {
//				TimeUnit.SECONDS.sleep(3);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//coffee1.change();
//			//coffee2.change();
//			//coffee3.change();
//		}
//		
//		if(arg0.getX()>=505 && arg0.getX()<= 635 && arg0.getY() >= 620 && arg0.getY() <= 705){
//			System.out.println("choc");
//		}
//		if(arg0.getX()>=chocBatter.getX() && arg0.getX()<= chocBatter.getX() + 135 && arg0.getY() >= chocBatter.getY() && arg0.getY() <= chocBatter.getY() + 105){
//			System.out.println("yes");
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
				if(px>=500 && px<= 780 && py >= 390 && py <= 540) {
					if(px>=500 && px<= 640 && py >= 390 && py <= 460 && !spot1) {
						objectList.get(i).setPosition(515, 370);
						spot1 = true;
						objectList.get(i).setInside(true, 515, 370);
					}
					if(px>=500 && px<= 640 && py >= 460 && py <= 540 && !spot2) {
						objectList.get(i).setPosition(505, 440);
						spot2 = true;
						objectList.get(i).setInside(true, 505, 440);
					}
					if(px>=640 && px<= 780 && py >= 390 && py <= 460 && !spot3) {
						objectList.get(i).setPosition(635, 370);
						spot3 = true;
						objectList.get(i).setInside(true, 635, 370);
					}
					if(px>=640 && px<= 780 && py >= 460 && py <= 540 && !spot4) {
						objectList.get(i).setPosition(645, 440);
						spot4 = true;
						objectList.get(i).setInside(true, 645, 440);
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
		
		for(int i = 0; i < objectList.size(); i++) {
			if (objectList.get(i).getBounds().contains(mp)) {
				if(px>=940 && px<= 1130 && py >= 375 && py <= 790) {
					if(px>=940 && px<= 1130 && py >= 375 && py <= 450 && !oven1) {
						objectList.get(i).setPosition(950, 375);
						oven1 = true;
						objectList.get(i).setInside(true, 950, 375);
						objectList.get(i).ovenChange(); 
					}
					if(px>=940 && px<= 1130 && py >= 455 && py <= 530 && !oven2) {
						objectList.get(i).setPosition(945, 455);
						oven2 = true;
						objectList.get(i).setInside(true, 945, 455);
						objectList.get(i).ovenChange(); 
					}
					if(px>=915 && px<= 1105 && py >= 535 && py <= 710 && !oven3) {
						objectList.get(i).setPosition(915, 535);
						oven3 = true;
						objectList.get(i).setInside(true, 915, 535);
						objectList.get(i).ovenChange(); 
					}
					if(px>=915 && px<= 1105 && py >= 615 && py <= 790 && !oven4) {
						objectList.get(i).setPosition(915, 615);
						oven4 = true;
						objectList.get(i).setInside(true, 915, 615);
						objectList.get(i).ovenChange(); 
					}
				}else if(objectList.get(i).isInside()) {
					objectList.get(i).setPosition(objectList.get(i).getInsideX(), objectList.get(i).getInsideY());
				}else {
					objectList.remove(i);
				}
			}
		}
//		g.drawRect(500, 390, 140, 70);
//		g.drawRect(500, 460, 140, 80);
//		g.drawRect(640, 390, 140, 70);
//		g.drawRect(640, 460, 140, 80);
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
