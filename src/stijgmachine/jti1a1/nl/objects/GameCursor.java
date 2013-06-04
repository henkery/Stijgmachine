package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import stijgmachine.jti1a1.nl.controller.Main;

public class GameCursor extends GameObject {

	public GameCursor() {
		super(0, 0, GameObject.ABSOLUTE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.draw(new Rectangle2D.Double(x,y,10,10));

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void update(int x, int y) {
		this.x = (int) (Main.resX/1024f*x);
		this.y = (int) (Main.resY/768f*y);
	}

}
