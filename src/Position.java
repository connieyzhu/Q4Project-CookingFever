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
