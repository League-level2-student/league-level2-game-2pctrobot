package GameDemo;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    
    int currentState = MENU;
	@Override
	public void paintComponent(Graphics g){
		g.fillRect(10, 10, 100, 100);
	}
	
}
