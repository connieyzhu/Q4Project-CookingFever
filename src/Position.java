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
	int[] wait = {1200, 3000, 5000, 8000};
	String[] names = {"Daphne", "Linda", "Francis", "Kyle"};
	boolean[] available = {true, true, true, true};
	boolean[] availableWait = {true, true, true, true};
	
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
		int count = 0;
		for(int i = 0; i < available.length; i++) {
			if(!available[i]) {
				count++;
			}
		}
		if(count == 4) {
			for(int i = 0; i < available.length; i++) {
				available[i] = true;
			}
		}
		count = 0;
	}
	
	public int getWait() {
		newNum();
		updateWaitAvail();
		if(!availableWait[num]) {
			newNum();
			getWait();
		}
		availableWait[num] = false;
		return wait[num];
	}
	
	public int newNum() {
		num = ThreadLocalRandom.current().nextInt(0, 4);
		return num;
	}
	
	public int getX() {
		newNum();
		updateXAvail();
		if(!available[num]) {
			newNum();
			getX();
		}
		available[num] = false;
		return arr[num];
	}
}
