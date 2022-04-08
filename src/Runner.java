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

public class Runner extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {
	Background cafeBg = new Background(0, 0, "/imgs/CafeBG.png");
	Background cafeCounter = new Background(0, 0, "/imgs/CafeCounter.png");
	Position pos = new Position("Person");
	Customer francis = new Customer(pos.getX(), 130, "Francis");
	Customer daphne = new Customer(pos.getX(), 130, "Daphne");
	Customer kyle = new Customer(pos.getX(), 130, "Kyle");
	Customer linda = new Customer(pos.getX(), 130, "Linda");
	OrderTimer custTimer1 = new OrderTimer(110, 75, 1);
	OrderTimer custTimer2 = new OrderTimer(400, 75, 1);
	OrderTimer custTimer3 = new OrderTimer(690, 75, 1);
	OrderTimer custTimer4 = new OrderTimer(980, 75, 1);
	Background order1 = new Background(20, 70, "/imgs/Order Bubble.png");
	Background order2 = new Background(310, 70, "/imgs/Order Bubble.png");
	Background order3 = new Background(600, 70, "/imgs/Order Bubble.png");
	Background order4 = new Background(890, 70, "/imgs/Order Bubble.png");
	
	//Person: (100, 130), (390, 130), (680,130), 970, 130)
	//Order Form: (20, 70), (310, 70), (600, 70), 890, 70)
	//Timer: (110, 75), (400, 75), (690, 75), (980, 75)
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	 
	public void paint(Graphics g) {
		cafeBg.paint(g);
		daphne.paint(g);
		francis.paint(g);
		kyle.paint(g);
		linda.paint(g);
		cafeCounter.paint(g);
		custTimer1.paint(g);
		custTimer2.paint(g);
		custTimer3.paint(g);
		custTimer4.paint(g);
		order1.paint(g);
		order2.paint(g);
		order3.paint(g);
		order4.paint(g);
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
		
	}
	
	
	//@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	//@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	//@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	//@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	//@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		 
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
		
	}

}









