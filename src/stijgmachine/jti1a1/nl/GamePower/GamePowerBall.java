package stijgmachine.jti1a1.nl.GamePower;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import stijgmachine.jti1a1.nl.objects.phys2d.Circle;

public class GamePowerBall extends Circle
{
	private int radius;
	public GamePowerBall(float f, int i, int j, int relativeFromTopleft) {
		super(f, i, j, relativeFromTopleft);
		// TODO Auto-generated constructor stub
		radius = (int) f;
	}
	
//	public void getImage()
//	{
//		ImageIcon image = new ImageIcon("src/stijgmachine.jti1a1.nl.GamePower/");
//	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		java.awt.Shape cirk = new Ellipse2D.Double(x2-radius,y2-radius,radius*2,radius*2);
		g.fill(cirk);
		
	}

}
