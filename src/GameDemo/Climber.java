package GameDemo;

import java.awt.Color;
import java.awt.Graphics;

public class Climber extends GObjaw{
	public boolean left = false;
	public boolean right = false;
	
	private int xVelocity = 5;
	
	int gravity = 1;
	int yVelocty = 0;
	int jumpPower = 20;
	
	int yLimit = 500;

	boolean canJump = false;
	public Climber(int xs, int ys, int w, int h){
		super(xs, ys, w, h);
		this.x = xs;
		this.y = ys;
		this.width = w;
		this.height = h;
		
		speed = 10;
	}
	
	public void jump(){
		if(canJump){
			yVelocty -= jumpPower;
			canJump = false;
		}
	}
	
	public void update(){
		if(left){
			x -= xVelocity;
		}
		if(right){
			x += xVelocity;
		}
		
		yVelocty += gravity;
		y += yVelocty;
		
		if(y >= yLimit){
			y = yLimit;
			yVelocty = 0;
			canJump = true;
		}
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
