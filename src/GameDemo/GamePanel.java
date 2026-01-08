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
    int currentState = MENU;
    public static boolean needImage = true;
	public static boolean gotImage = false;
	public static BufferedImage image;
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
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 1000, 1000);
		}else if(currentState == GAME){
			g.setColor(Color.black);
			g.fillRect(0, 0, 1000, 1000);
		}else if(currentState == END){
			g.setColor(Color.red);
			g.fillRect(0, 0, 1000, 1000);
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
	void drawMenuState(Graphics g) {  
		
	}
	void drawGameState(Graphics g) {  
		
	}
	void drawEndState(Graphics g)  {  
		
	}
	void updateMenuState() {  
		
	}
	void updateGameState() {  
		
	}
	void updateEndState()  { 
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
