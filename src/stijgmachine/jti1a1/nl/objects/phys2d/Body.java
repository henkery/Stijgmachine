package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;
import java.awt.Point;

import net.phys2d.math.ROVector2f;
import net.phys2d.raw.forcesource.ForceSource;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class Body extends GameObject {
	
	private net.phys2d.raw.Body body;
	private Circle shape;

	public Body(Circle circle, float f) {
		super(0, 0, 0);
		body = new net.phys2d.raw.Body(circle.getShape(), f);
		shape = circle;
		// TODO Auto-generated constructor stub
	}

	public Body() {
		super(0, 0, 0);
		// TODO Auto-generated constructor stub
	}

	public void setPosition(float f, float g) {
		body.setPosition(f, g);
		
	}

	public ROVector2f getPosition() {
		// TODO Auto-generated method stub
		return body.getPosition();
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		if (shape != null)
			shape.draw(g, height, width,(int) body.getPosition().getX(),(int) body.getPosition().getY());
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public net.phys2d.raw.shapes.Shape getShape() {
		// TODO Auto-generated method stub
		return shape.getShape();
	}

	public net.phys2d.raw.Body getBody() {
		// TODO Auto-generated method stub
		return body;
	}

}
