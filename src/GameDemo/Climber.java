package GameDemo;

import java.awt.Color;
import java.awt.Graphics;

public class Climber extends GObjaw{
	public Climber(int xs, int ys, int w, int h) {
		super(xs, ys, w, h);
	}
	public void draw(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);

	}
}
