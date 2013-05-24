package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class Line extends Shape {

	private net.phys2d.raw.shapes.Line line;
	
	private int x2,y2;
	
	public Line(float i, float j, int k, int l, int relativeFromTopleft) {
		super((int)i,(int)j,relativeFromTopleft);
		x2=k;
		y2=l;
		line = new net.phys2d.raw.shapes.Line(i, j, k, l);
		// TODO Auto-generated constructor stub
	}

	public net.phys2d.raw.shapes.Shape getShape() {
		// TODO Auto-generated method stub
		return line;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.draw(new Line2D.Double(new Point2D.Double(x, y), new Point2D.Double(this.x2, this.y2)));
		System.out.println("line drawn");
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
