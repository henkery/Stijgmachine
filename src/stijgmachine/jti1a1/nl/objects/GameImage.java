package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameImage extends GameObject {

	private BufferedImage img;
	private int h;
	private int w;

	public GameImage(int x, int y, int relativeTo, BufferedImage img) {
		super(x, y, relativeTo);
		this.img = img;
		this.h = 0;
		this.w = 0;
		// TODO Auto-generated constructor stub
	}

	public GameImage(int x, int y, int h, int w, int relativeTo,
			BufferedImage img2) {
		super(x, y, relativeTo);
		this.img = img2;
		this.h = h;
		this.w = w;
	}

	@Override
	public void click() {

	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		if (h != 0)
			g.drawImage(img, x, y, h, w, null);
		else
			g.drawImage(img, null, x, y);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
