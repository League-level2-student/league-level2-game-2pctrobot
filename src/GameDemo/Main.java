package GameDemo;

import javax.swing.JFrame;

public class Main {
	JFrame frame;
	GamePanel tower;
	final int WIDTH = 1000;
	final int HEIGHT = 1000;

	public Main() {
		frame = new JFrame();
		tower = new GamePanel();
	}

	public static void main(String[] args) {

		Main rust = new Main();
		rust.setup();
	}

	private void setup() {
		frame.add(tower);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
