package GameDemo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class JumperDemo extends JPanel implements ActionListener, KeyListener{
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	boolean stopper;
	
	JFrame window;
	Timer timer;
	
	Player p1 = new Player(50, 50, 50, 50, false, Color.ORANGE);
	ArrayList<Player> sickos = new ArrayList<Player>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	public static void main(String[] args) {
		new JumperDemo().run();
	}
	
	public void run(){
		window = new JFrame("Tower");
		window.addKeyListener(this);
		window.add(this);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();
		timer = new Timer(1000 / 60, this);
		Random rand = new Random();
        int randomPos = rand.nextInt(300);
        int randomVar1 = rand.nextInt(300);
        int randomVar2 = rand.nextInt(300);
        int randomVar3 = rand.nextInt(300);
        
		
		platforms.add(new Platform(randomPos, 575, 200, 50));
		platforms.add(new Platform(randomPos+randomVar1, 675, 200, 50));
		platforms.add(new Platform(randomPos+randomVar2, 775, 200, 50));
		platforms.add(new Platform(randomPos+randomVar3, 875, 200, 50));
		
		sickos.add(new Player(randomPos, 575, 50, 50, false, Color.RED));
		
		timer.start();
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		p1.draw(g);
		
		for(Player p : sickos){
			p.draw(g);
		}
		
		for(Platform p : platforms){
			p.draw(g);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		checkCollision();

		p1.update();
		
		for(Player p : sickos){
			p.update();
			if(p1.getX()<p.getX()) {
				p.left();
			}
			if(p1.getX()>p.getX()) {
				p.right();
			}
			if(p1.getY()<p.getY()) {
				p.jump();
			}
		}
		
		for(Platform p : platforms){
			p.update();
		}
		
		repaint();
		
	}
	
	private void checkCollision(){
		stopper = false;
		for(Platform p: platforms){
			if(p1.getCBox().intersects(p.getCBox()) && !stopper){
				handleCollision(p, p1);
				System.out.println("collided p1");
				stopper = true;
			}
		}
		if(!stopper) {
			p1.setYLimit(950);
		}
		for(Player s: sickos){
			stopper = false;
			for(Platform p: platforms) {
				if(s.getCBox().intersects(p.getCBox()) && !stopper){
					handleCollision(p, s);
					System.out.println("collided");
					stopper = true;
				}
				if(!stopper) {
					s.setYLimit(950);
				}
			}
		}
		
		
		
		
	}
	

	
	private void handleCollision(Platform p, Player u){
		if(u.getYVelocity() >= 0 && u.getY() + u.getHeight() < p.getY() + 25){
			u.setYLimit(p.getY() - u.getHeight());
			System.out.println("p1 set to " + (p.getY() - u.getHeight()));
		}else{
			u.setYLimit(950);
		}
		
		
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = true;
		}

		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.jump();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			timer.stop();
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = false;
		}
	}
}






