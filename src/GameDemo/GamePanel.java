package GameDemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    Timer frameDraw;
    int currentState = GAME;
    public static boolean needImage = true;
	public static boolean gotImage = false;
	public static BufferedImage image;
	Climber scrap = new Climber(250, 700, 50, 50);
    public GamePanel() {
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();

		if (needImage) {
			loadImage("rust.pixil");
		}
	}

    
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}
	}
	
	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
	public void drawMenuState(Graphics g) {  
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);
	}
	public void drawGameState(Graphics g) { 
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 1000, 1000);
		System.out.println("1");
		scrap.draw(g);
	}
	public void drawEndState(Graphics g)  {  
		g.setColor(Color.red);
		g.fillRect(0, 0, 1000, 1000);
	}
	public void updateMenuState() {  
	//	drawMenuState();
	}
	public void updateGameState() {  
		//drawMenuState();
	}
	public void updateEndState()  { 
	//	drawMenuState();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}   
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			scrap.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			scrap.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			scrap.jump();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			scrap.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			scrap.right = false;
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("Action");
		repaint();
	}
	
}
