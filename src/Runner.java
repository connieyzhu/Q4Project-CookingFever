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
	Customer daphne = new Customer(300, 130, "Daphne");
	Customer francis = new Customer(100, 130, "Francis");
	Customer kyle = new Customer(500, 130, "Kyle");
	Customer linda = new Customer(700, 130, "Linda");
	//OrderTimer custTimer = new OrderTimer(300, 70, 5);
	OrderTimer custTimer = new OrderTimer(300, 75, 1);
	Background order = new Background(210, 70, "/imgs/Order Bubble.png");
	
	int mouseY = MouseInfo.getPointerInfo().getLocation().y; 
	int mouseX = MouseInfo.getPointerInfo().getLocation().x;
	 
	public void paint(Graphics g) {
		cafeBg.paint(g);
		daphne.paint(g);
		francis.paint(g);
		kyle.paint(g);
		linda.paint(g);
		cafeCounter.paint(g);
		custTimer.paint(g);
		order.paint(g);
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









