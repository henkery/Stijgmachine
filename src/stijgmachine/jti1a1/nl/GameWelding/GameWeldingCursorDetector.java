package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import stijgmachine.jti1a1.nl.objects.GameObject;


public class GameWeldingCursorDetector extends GameObject
{
	private boolean left = false;
	Rectangle2D rectangle;
	
	public GameWeldingCursorDetector(String s, int x, int y)
	{
		super(x, y, 204);
		if(s == "left")
		{
			rectangle = new Rectangle2D.Double((x+200)-25, y-25, 50, 50);
			left = true;
		}
		if(s == "right")
		{
			rectangle = new Rectangle2D.Double(x-25, y-25, 50, 50);
			left = false;
		}
	}
	
	public void update(int x, int y)
	{
		if(!left)
		rectangle.setRect(x-25, y-25, 50, 50);
		else
		rectangle.setRect((x+200)-25, y-25, 50, 50);
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
	}
	
	public Rectangle2D getRectangle()
	{
		return rectangle;
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void click()
	{
		// TODO Auto-generated method stub
		
	}

}
