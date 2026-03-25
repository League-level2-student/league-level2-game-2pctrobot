package GameDemo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	Random rand = new Random();
	
	boolean stopper;
	
	JFrame window;
	Timer timer;
	
	Player p1 = new Player(50, 50, 30, 30, false, Color.ORANGE);
	ArrayList<Player> sickos = new ArrayList<Player>();
	ArrayList<Player> maniacs = new ArrayList<Player>();
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	ArrayList<Bullet> shot = new ArrayList<Bullet>();
	public int charges = 0;
	public long lastSpawn;
	
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
		
        int randomPos = rand.nextInt(300);
        int randomVar1 = rand.nextInt(300);
        int randomVar2 = rand.nextInt(300);
        int randomVar3 = rand.nextInt(300);
		
		platforms.add(new Platform(randomPos, 595, 200, 30));
		platforms.add(new Platform(randomPos+randomVar1, 295, 200, 30));
		platforms.add(new Platform(randomPos+randomVar2, 395, 200, 30));
		platforms.add(new Platform(randomPos+randomVar3, 495, 200, 30));
		platforms.add(new Platform(randomPos+275, 595, 200, 30));
		platforms.add(new Platform(randomPos+randomVar1+275, 295, 200, 30));
		platforms.add(new Platform(randomPos+randomVar2+275, 395, 200, 30));
		platforms.add(new Platform(randomPos+randomVar3+275, 495, 200, 30));
		platforms.add(new Platform(randomPos+550, 595, 200, 30));
		platforms.add(new Platform(randomPos+randomVar1+550, 295, 200, 30));
		platforms.add(new Platform(randomPos+randomVar2+550, 395, 200, 30));
		platforms.add(new Platform(randomPos+randomVar3+550, 495, 200, 30));
		platforms.add(new Platform(randomPos-275, 595, 200, 30));
		platforms.add(new Platform(randomPos+randomVar1-275, 295, 200, 30));
		platforms.add(new Platform(randomPos+randomVar2-275, 395, 200, 30));
		platforms.add(new Platform(randomPos+randomVar3-275, 495, 200, 30));
		platforms.add(new Platform(randomPos-550, 595, 200, 30));
		platforms.add(new Platform(randomPos+randomVar1-550, 295, 200, 30));
		platforms.add(new Platform(randomPos+randomVar2-550, 395, 200, 30));
		platforms.add(new Platform(randomPos+randomVar3-550, 495, 200, 30));
		
		sickos.add(new Player(randomPos, 700, 30, 30, false, Color.RED));
		
		
		lastSpawn = System.currentTimeMillis();
		timer.start();
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Font scoreFont = new Font("Arial", Font.PLAIN, 20);
		g.setFont(scoreFont);
		g.setColor(Color.WHITE);
		
		g.drawString("Charges: "+charges, 180, 30);
		
		p1.draw(g);
		
		for(Player p : sickos){
			p.draw(g);
		}
		
		for(Player p : maniacs){
			p.draw(g);
		}
		
		for(Bullet b : shot){
			b.draw(g);
		}
		
		for(Platform p : platforms){
			p.draw(g);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		checkCollision();
		checkEnemy();
		p1.update();
		long charging = System.currentTimeMillis();
		if((charging-lastSpawn)>25000) {
			charges ++;
		}
		if((charging-lastSpawn)>35000) {
			int spawnX = rand.nextInt(1000);
			int spawnY = rand.nextInt(20);
			sickos.add(new Player(spawnX, 800, 30, 30, false, Color.RED));
		}
		
		for(Player p : sickos){
			p.update();
			if(p1.getX()<p.getX()) {
				p.left();
			}
			if(p1.getX()>p.getX()) {
				p.right();
			}
			if(p1.getY()<p.getY()) {
				p.ejump();
			}
			if(p1.getY()>p.getY()) {
				p.drop();
			}
		}
		
		for(Platform p : platforms){
			p.update();
		}
		
		for(Bullet b : shot){
			b.update();
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
			p1.setYLimit(970);
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
					s.setYLimit(970);
					//Make sure it can only damage a player once it hits the floor for the first time.
				}
			}
		}
		
		
		
		
	}
	
	private void checkEnemy(){
		for(Player s: sickos){
			if(s.getCBox().intersects(p1.getCBox())) {
				//System.exit(0);
				System.out.println("killed");
			}
		}
		Player toberemoved=null;
		Bullet toberemoved2=null;
		for(Player s: sickos){
			for(Bullet b: shot) {
				if(s.getCBox().intersects(b.getCBox())){
					toberemoved=s;
					toberemoved2=b;
				
				}
			}
		}
	sickos.remove(toberemoved);
	shot.remove(toberemoved2);
		
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
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.drop();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			timer.stop();
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A){
			shot.add(new Bullet(p1.getX()-15, p1.getY()+15, 5, 5, 3, 1, Color.MAGENTA));
			charges--;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			shot.add(new Bullet(p1.getX()+42, p1.getY()+15, 5, 5, 3, 2, Color.MAGENTA));
			charges--;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			shot.add(new Bullet(p1.getX()+15, p1.getY()-15, 5, 5, 1, 3, Color.MAGENTA));
			charges--;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			shot.add(new Bullet(p1.getX()+15, p1.getY()+45, 5, 5, 2, 3, Color.MAGENTA));
			charges--;
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){
			maniacs.add(new Player(p1.getX(), p1.getY()+30, 30, 30, false, Color.MAGENTA));
			charges--;
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






