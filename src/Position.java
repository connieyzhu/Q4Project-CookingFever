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
	int[] arr = new int[4];
	int[] person = {100, 390, 680, 970};
	int[] order = {20, 310, 600, 890};
	int[] timer = {110, 400, 690, 980};
	boolean[] available = {true, true, true, true};
	
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
	}
	
	public int newNum() {
		num = ThreadLocalRandom.current().nextInt(0, 4);
		return num;
	}
	
	public int getX() {
		newNum();
		if(!available[num]) {
			newNum();
			getX();
		}
		available[num] = false;
		return arr[num];
	}
}
