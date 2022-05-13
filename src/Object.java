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
	boolean filled = false; 
	private Rectangle rectangle;
	private boolean insideSquares;
	private int insideX, insideY;
	

	public Object(int x, int y, String s){
		this.x = x;
		this.y = y; 
		type = s;
		if(type.equals("Blueberry")) {
			img = getImage("/imgs/Blueberry.png");
			rectangle = new Rectangle(x, y, 100, 55);
		}
		if(type.equals("ChocBlueBake")) {
			img = getImage("/imgs/ChocBlueBake.png");
			rectangle = new Rectangle(x, y, 125, 95);
		}
		if(type.equals("ChocBlueBatter")) {
			img = getImage("/imgs/ChocBlueUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocStrawBake")) {
			img = getImage("/imgs/ChocStrawBake.png");
			rectangle = new Rectangle(x, y, 125, 95);
		}
		if(type.equals("ChocStrawBatter")) {
			img = getImage("/imgs/ChocStrawUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("ChocBatter")) {
			img = getImage("/imgs/ChocUnbake.png");
			rectangle = new Rectangle(x + 32, y, 125, 95);
		}
		if(type.equals("Coffee")) {
			img = getImage("/imgs/CoffeeEmpty.png");
			rectangle = new Rectangle(x, y, 58, 60);
		}
		if(type.equals("Strawberry")) {
			img = getImage("/imgs/Strawberry.png");
			rectangle = new Rectangle(x, y, 105, 60);
		}
		if(type.equals("VanBlueBake")) {
			img = getImage("/imgs/VanBlueBake.png");
			rectangle = new Rectangle(x, y, 125, 95);
		}
		if(type.equals("VanBlueBatter")) {
			img = getImage("/imgs/VanBlueUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanStrawBake")) {
			img = getImage("/imgs/VanStrawBake.png");
			rectangle = new Rectangle(x, y, 125, 95);
		}
		if(type.equals("VanStrawBatter")) {
			img = getImage("/imgs/VanStrawUnbake.png");
			rectangle = new Rectangle(x, y, 125, 85);
		}
		if(type.equals("VanBatter")) {
			img = getImage("/imgs/VanUnbake.png");
			rectangle = new Rectangle(x + 32, y, 125, 95);
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
		tx.scale(1.0, 1.0);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.04,.04);
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
		filled = true; 
	}
	
	public void ovenChange() {
		if(type.equals("VanStrawBake")) {
			changePicture("/imgs/VanStrawOven.png");
		}
		
		if(type.equals("VanBlueBake")) {
			changePicture("/imgs/VanBlueOven.png");
		}
		
		if(type.equals("ChoocStrawBake")) {
			changePicture("/imgs/ChocStrawOven.png");
		}
		
		if(type.equals("ChocBlueBake")) {
			changePicture("/imgs/ChocBlueOven.png");
		}
	}
	
	public void bakeChange() {
		if(type.equals("VanStrawBake")) {
			changePicture("/imgs/VanStrawBake.png");
		}
		
		if(type.equals("VanBlueBake")) {
			changePicture("/imgs/VanBlueBake.png");
		}
		
		if(type.equals("ChoocStrawBake")) {
			changePicture("/imgs/ChocStrawBake.png");
		}
		
		if(type.equals("ChocBlueBake")) {
			changePicture("/imgs/ChocBlueBake.png");
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
		tx.setToTranslation(x, y);
		tx.scale(1,1);
	}
	
	public boolean objectExit() {
		//g.drawRect(500, 390, 280, 150);
		if(x < 500 || x > 780 || y < 390 || y > 540) {
			return true;
		}
		return false;
	}
}
