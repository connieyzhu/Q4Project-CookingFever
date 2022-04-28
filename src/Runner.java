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
	
	private ArrayList<Coffee> coffeeList;
	private Coffee hitBox;
    private Point offset;
	
	Background cafeBg = new Background(0, 0, "/imgs/CafeBG.png");
	Background cafeCounter = new Background(0, 0, "/imgs/CafeCounterv2.png");
	Position pos = new Position("timer");
	
	OrderTimer timer = new OrderTimer(pos.getX(), 75, 1);
	OrderTimer timer1 = new OrderTimer(pos.getX(), 75, 1);
	OrderTimer timer2 = new OrderTimer(pos.getX(), 75, 1);
	OrderTimer timer3 = new OrderTimer(pos.getX(), 75, 1);
	
	Coffee coffee1 = new Coffee(195, 485); 
	Coffee coffee2 = new Coffee(255, 490);
	Coffee coffee3 = new Coffee(315, 495);
	
	ChocolateBatter chocBatter = new ChocolateBatter(475, 610);
	
	/*Customer francis = new Customer(pos.getX(), 130, "Francis");
	Customer daphne = new Customer(pos.getX(), 130, "Daphne");
	Customer kyle = new Customer(pos.getX(), 130, "Kyle");
	Customer linda = new Customer(pos.getX(), 130, "Linda");*/
	
	//Person: (100, 130), (390, 130), (680,130), 970, 130)
	//Order Form: (20, 70), (310, 70), (600, 70), 890, 70)
	//Timer: (110, 75), (400, 75), (690, 75), (980, 75)
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	private boolean b; 
	
	 
	public void paint(Graphics g) {
		cafeBg.paint(g);
		/*daphne.paint(g);
		francis.paint(g);
		kyle.paint(g);
		linda.paint(g);*/
		cafeCounter.paint(g);
		timer.paint(g);
		timer1.paint(g);
		timer2.paint(g);
		timer3.paint(g);
		coffee1.paint(g);
		coffee2.paint(g);
		coffee3.paint(g);
		chocBatter.paint(g);
		
		
		g.drawRect(505, 620, 130, 85);
		g.drawRect(645, 620, 130, 85);
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
		
		coffeeList = new ArrayList<>();
		coffeeList.add(coffee1);
		coffeeList.add(coffee2);
	}
	
	
	public Rectangle coffeeGetRect() {
		return new Rectangle(170, 365, 220, 210);
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
		Point mp = arg0.getPoint();
		for (Coffee box : coffeeList) {
			if (box.getBounds().contains(mp)) {
				hitBox = box;
				System.out.println("hi");
				offset = new Point();
				offset.x = mp.x - box.getBounds().x;
				offset.y = mp.y - box.getBounds().y;
			}
		}

	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
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
		if (hitBox != null) {
			Point mp = e.getPoint();
			Rectangle bounds = hitBox.getBounds();
			bounds.x = mp.x - offset.x;
			bounds.y = mp.y - offset.y;
			for(Coffee box: coffeeList) {
				if(box.equals(hitBox)) {
					box.setPosition(bounds.x, bounds.y);
				}
			}
		}
	}

}
