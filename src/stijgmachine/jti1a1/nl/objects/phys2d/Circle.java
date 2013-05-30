package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.awt.*;

import stijgmachine.jti1a1.nl.objects.GameObject;
import net.phys2d.raw.shapes.DynamicShape;

public class Circle extends Shape {
	
	private net.phys2d.raw.shapes.Circle circle;
	private float radius;
	
	
	
	public Circle(float f, int i, int j, int relativeFromTopleft) {
		super(0,0,0);
		this.radius = f;
		circle = new net.phys2d.raw.shapes.Circle(f);
		// TODO Auto-generated constructor stub
	}

	public DynamicShape getShape() {
		// TODO Auto-generated method stub
		return circle;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		java.awt.Shape cirk = new Ellipse2D.Double(x2,y2,radius*2,radius*2);
		g.fill(cirk);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
