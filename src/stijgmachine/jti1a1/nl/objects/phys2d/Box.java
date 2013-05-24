package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import stijgmachine.jti1a1.nl.objects.GameObject;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Shape;

public class Box extends stijgmachine.jti1a1.nl.objects.phys2d.Shape {
	
	private net.phys2d.raw.shapes.Box box;
	
	private float h,w;

	public Box(float f, float g, int i, int j, int relativeFromTopleft) {
		super(i,j,relativeFromTopleft);
		h=f;
		w=g;
		box = new net.phys2d.raw.shapes.Box(f, g);
		// TODO Auto-generated constructor stub
	}

	public DynamicShape getShape() {
		// TODO Auto-generated method stub
		return box;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		g.fill(new Rectangle2D.Double(x,y,h,w));
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
