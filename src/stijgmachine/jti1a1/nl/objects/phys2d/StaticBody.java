package stijgmachine.jti1a1.nl.objects.phys2d;

import java.awt.Graphics2D;

import stijgmachine.jti1a1.nl.GamePower.GamePowerObstacle;

import net.phys2d.raw.shapes.DynamicShape;


public class StaticBody extends Body {
	
	private net.phys2d.raw.StaticBody staticBody;
	private Shape shape;
	
	public StaticBody(String string,DynamicShape shape)
	{
		super();
		staticBody = new net.phys2d.raw.StaticBody(string, shape);
		this.shape = (Shape)shape;
	}
	
	public StaticBody(String string,Shape shape)
	{
		super();
		staticBody = new net.phys2d.raw.StaticBody(string, shape.getShape());
		this.shape = shape;
	}
	
	
	public StaticBody(String string, Box box) {
		super();
		staticBody = new net.phys2d.raw.StaticBody(string, box.getShape());
		shape = box;
	}

	public StaticBody(Line line) {
		super();
		staticBody = new net.phys2d.raw.StaticBody(line.getShape());
		shape = line;
	}

	public StaticBody(Box box) {
		super();
		staticBody = new net.phys2d.raw.StaticBody(box.getShape());
		shape = box;
	}

	public StaticBody(String string, Line line) {
		super();
		staticBody = new net.phys2d.raw.StaticBody(string, line.getShape());
		shape = line;
	}

	public StaticBody(String string, GamePowerObstacle box) {
		super();
		staticBody = new net.phys2d.raw.StaticBody(string, box.getShape());
		shape = box;
	}

	public void setPosition(float f, float g) {
		staticBody.setPosition(f, g);
		shape.setLocation(f,g);
		
	}
	
	@Override
	public net.phys2d.raw.Body getBody() {
		return staticBody;
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		shape.draw(g, height, width, x2, y2);
	}

}
