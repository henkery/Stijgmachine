package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingWeld extends GameObject
{
	private GeneralPath path = null;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public GameWeldingWeld()
	{
		super(0, 0, GameObject.ABSOLUTE);
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x3, int y3)
	{
		//g.setColor(Color.orange);
		//g.drawLine(x1, y1, x2, y2);
		if(path != null)
		{
			g.setColor(Color.orange);
			g.setStroke(new BasicStroke(20.0f, 1, 1));
			g.draw(path);
		}
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
	
	public void update(int x, int y)
	{
		this.x2 = x1;
		this.y2 = y1;
		this.x1 = x;
		this.y1 = y;
	}
	
	public void startWeld(int x, int y)
	{
		if(path == null)
		{
			path = new GeneralPath();
			path.moveTo(x, y);
		}
		else path.lineTo(x, y);
		
	}
	
	public void stopWeld()
	{
		//path.closePath();
		path = null;
		
//		path.
	}
	
	public boolean isStarted()
	{
		if(this.path != null)
		{
			return true;
		}
		return false;
	}
	
	public GeneralPath getPath()
	{
		return path;
	}
	
	public int getX()
	{
		return x2;
	}

	public int getY()
	{
		return y2;
	}

}
