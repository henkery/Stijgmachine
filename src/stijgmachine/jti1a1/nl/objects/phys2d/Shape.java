package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;

import stijgmachine.jti1a1.nl.objects.GameObject;

public abstract class Shape extends GameObject {

	public Shape(int x, int y, int relativeTo) {
		super(x, y, relativeTo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void click();

	@Override
	public abstract void draw(Graphics2D g, int height, int width, int x2, int y2);
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLocation(float f, float g) {
		x=(int) f;
		y=(int) g;
		
	}

	public abstract net.phys2d.raw.shapes.DynamicShape getShape(); 

}
