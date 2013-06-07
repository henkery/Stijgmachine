package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingWeld extends GameObject
{
	private GeneralPath path = null;
	private ArrayList<GeneralPath> pathList;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public GameWeldingWeld()
	{
		super(0, 0, GameObject.ABSOLUTE);
		pathList = new ArrayList<GeneralPath>();
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x3, int y3)
	{
		if(path != null)
		{
			g.setColor(Color.orange);
			g.setStroke(new BasicStroke(20.0f, 1, 1));
			g.draw(path);
		}
		if(!pathList.isEmpty())
		{
			for(GeneralPath p : pathList)
			{
				g.setColor(Color.orange);
				g.setStroke(new BasicStroke(20.0f, 1, 1));
				g.draw(p);
			}
		}
	}

	@Override
	public int getID()
	{
		return 0;
	}

	@Override
	public void click()
	{		
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
		else continueWeld(x, y);	
	}
	
	public void continueWeld(int x, int y)
	{
		if(isStarted())
		{
			path.lineTo(x, y);
		}
	}
	
	public void saveWeld()
	{
		if(path != null)
		{
			pathList.add(path);
			stopWeld();
		}
	}
	
	public void stopWeld()
	{
		path = null;
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
