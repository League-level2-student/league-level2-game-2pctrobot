package GameDemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player{
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean enemy;
	private Color colour;
	
	private Rectangle cBox = new Rectangle();
	
	public boolean left = false;
	public boolean right = false;
	
	private int xVelocity = 5;
	
	private int gravity = 1;
	private int yVelocity = 0;
	private int jumpPower = 16;
	
	private int yLimit = 950;

	boolean canJump = false;
	
	public Player(int x, int y, int w, int h, boolean enemy, Color colour){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.enemy = enemy;
		this.colour = colour;
		
		cBox.setBounds(x, y, width, height);
	}
	
	public void jump(){
		if(canJump){
			yVelocity -= jumpPower;
			canJump = false;
		}
	}
	
	public void left(){
		x -= xVelocity*0.8;
	}
	
	public void right(){
		x += xVelocity*0.8;
	}
	
	public void velocityLeft() {
		
	}
	
	public void velocityRight() {
		
	}
	
	public void update(){
		if(enemy!=true) {
			if(left){
				x -= xVelocity;
			}
			if(right){
				x += xVelocity;
			}
		
			yVelocity += gravity;
			y += yVelocity;
		
			if(y >= yLimit + 1){
				y = yLimit + 1;
				yVelocity = 0;
				canJump = true;
			}
		}	
		
		if(enemy=true) {
			if(left){
				x -= xVelocity;
			}
			if(right){
				x += xVelocity;
			}
		
			yVelocity += gravity;
			y += yVelocity;
		
			if(y >= yLimit + 1){
				y = yLimit + 1;
				yVelocity = 0;
				canJump = true;
			}
		}
		cBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(colour);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
	
	public void setYLimit(int l){
		yLimit = l;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getYVelocity(){
		return yVelocity;
	}
}
