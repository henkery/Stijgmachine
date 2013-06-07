package stijgmachine.jti1a1.nl.GamePower;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import stijgmachine.jti1a1.nl.objects.phys2d.Line;

public class GamePowerLine extends Line
{
	private Point p1 = new Point(0,0);
	private Point p2 = new Point(0,0);
	
	public GamePowerLine(float i, float j, int k, int l, int relativeFromTopleft) {
		super(i, j, k, l, relativeFromTopleft);
		// TODO Auto-generated constructor stub
		p1 = new Point((int)i,(int)j);
		p2 = new Point(k,l);
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.setColor(new Color(182,126,44));
		g.setStroke(new BasicStroke(2.0f));
		g.draw(new Line2D.Double(new Point2D.Double(p1.x,p1.y), new Point2D.Double(p2.x,p2.y)));
		g.setColor(Color.BLACK);
		// TODO Auto-generated method stub
		
	}

}
