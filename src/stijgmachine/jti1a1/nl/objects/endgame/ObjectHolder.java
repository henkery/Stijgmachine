package stijgmachine.jti1a1.nl.objects.endgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameImage;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class ObjectHolder extends GameObject {
	
	private int h,w;
	private GameImage image;
	private BufferedImage doneImage;
	private int id;
	private boolean done;

	public ObjectHolder(int x, int y, int h, int w, int relativeTo, BufferedImage img) {
		super(x, y, relativeTo);
		this.h = h;
		this.w = w;
		if (img != null) {
			image = new GameImage(x,y,h,w,relativeTo, img);
		}
		id = 0;
	}

	public ObjectHolder(int x, int y, int h, int w,
			int id, int relativeTo, BufferedImage read, BufferedImage read2) {
		super(x, y, relativeTo);
		this.h = h;
		this.w = w;
		if (read != null) {
			image = new GameImage(x,y,h,w,relativeTo, read);
		}
		doneImage = read2;
		this.id = id;
	}

	@Override
	public void draw(Graphics2D g, int h, int wM, int x2, int y2) {
		Graphics2D g2 = g;
		if (image == null) {
			g2.setColor(Color.black);
			g2.fill(new Rectangle2D.Double(x, y, this.h, w));
		}
		else {
			image.predraw(g2, h, wM, x2, y2);
//			image.draw(g2, h, wM, x2, y2);
		}
//		System.out.println("loc: "+x+"x"+y);
	}

	@Override
	public void click() {
		if (!done) {
			if (doneImage != null) {
				image = new GameImage(x,y,h,w,relativeTo, doneImage);
			}
			done = true;
		}
	}
	
	public boolean isDone() {
		return done;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isWithin(int x, int y) {
		int[] loc = getPosition(this.x, this.y, 0, 0, Main.resY, Main.resX, relativeTo);
		int x2 = loc[0];
		int y2 = loc[1];
		System.out.println("checking if "+x+" is within "+this.x+"-"+(this.x+this.w));
		boolean bool = (x >= x2 && x <= x2+this.w) && (y >= y2 && y <= y2+this.h);
		System.out.println((bool)?"it is":"it isn't");
		return bool;
	}

	public boolean idMatch(int id) {
		// TODO Auto-generated method stub
		return this.id == id;
	}

	
}
