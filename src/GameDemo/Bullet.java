package GameDemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x;
	private int y;
	private int width;
	private int height;
	private int up;
	private int left;
	private Color colour;
	
	//Up: 1 = up, 2 = down, 3 = neutral
	//Left: 1 = right, 2 = left, 3 = neutral 
	
	private Rectangle cBox = new Rectangle();
	
	public Bullet(int x, int y, int w, int h, int up, int right, Color colour){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.up = up;
		this.colour = colour;
		
		cBox.setBounds(x, y, width, height);
	}
	
	public void update() {
		if(up == 1 && left == 3) {
			
		}else if(up == 2 && left == 3) {
			
		}else if(up == 3 && left == 1) {
			
		}else if(up == 3 && left == 2) {
			
		}
	}
	
	public void draw(Graphics g){
		g.setColor(colour);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
