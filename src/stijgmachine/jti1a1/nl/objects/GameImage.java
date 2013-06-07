package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameImage extends GameObject {

	private BufferedImage img;

	public GameImage(int x, int y, int relativeTo, BufferedImage img) {
		super(x, y, relativeTo);
		this.img = img;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {

	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.drawImage(img, null, x, y);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
