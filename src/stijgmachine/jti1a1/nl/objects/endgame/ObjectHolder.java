package stijgmachine.jti1a1.nl.objects.endgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import stijgmachine.jti1a1.nl.objects.GameImage;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class ObjectHolder extends GameObject {
	
	private int h,w;
	private GameImage image;

	public ObjectHolder(int x, int y, int h, int w, int relativeTo, BufferedImage img) {
		super(x, y, relativeTo);
		this.h = h;
		this.w = w;
		if (img != null) {
			image = new GameImage(x,y,relativeTo, img);
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g, int h, int wM, int x2, int y2) {
		Graphics2D g2 = g;
		if (image == null) {
			g2.setColor(Color.black);
			g2.fill(new Rectangle2D.Double(x, y, this.h, w));
		}
		else {
			image.draw(g2, h, wM, x2, y2);
		}
		System.out.println("loc: "+x+"x"+y);
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
