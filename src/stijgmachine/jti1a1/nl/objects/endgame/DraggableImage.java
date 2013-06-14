package stijgmachine.jti1a1.nl.objects.endgame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameImage;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class DraggableImage extends GameObject {

	private GameImage image;
	private int h;
	private int w;
	private int id;
	private boolean done;

	public DraggableImage(int x, int y, int width, int height,
			int id, int relativeTo, BufferedImage read) {
		super(x, y, relativeTo);
		this.image = new GameImage(x, y, width, height, relativeTo, read);
		this.h = height;
		this.w = width;
		this.id = id;
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		if (image != null)
			image.predraw(g, height, width, x2, y2);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub

	}
	
	public boolean isWithin(int x, int y) {
		int[] loc = getPosition(this.x, this.y, 0, 0, Main.resY, Main.resX, relativeTo);
		int x2 = loc[0];
		int y2 = loc[1];
		System.out.println("checking if "+x+" is within "+this.x+"-"+(this.x+this.w));
		System.out.println("checking if "+y+" is within "+this.y+"-"+(this.y+this.h));
		boolean bool = (x >= x2 && x <= x2+this.w) && (y >= y2 && y <= y2+this.h);
		System.out.println((bool)?"it is":"it isn't");
		return bool;
	}
	
	
	public void update(int ax, int ay) {
		this.x = (int) (Main.resX/1024f*ax);
		this.y = (int) (Main.resY/768f*ay);
		image.update(x,y);
		System.out.println("I am dragged");
//		throw new Exception();
	}

	public int getid() {
		// TODO Auto-generated method stub
		return id;
	}

	public void finish() {
		x = Main.resX+100;
		y = Main.resY+100;
		image = null;
		done = true;
	}
	
	public boolean isDone() {
		return done;
	}

}
