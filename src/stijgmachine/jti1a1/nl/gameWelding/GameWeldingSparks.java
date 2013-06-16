package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingSparks extends GameObject
{
	private ArrayList<Particle> list = new ArrayList<Particle>();
	private Rectangle2D rect;
	private int x;
	private int y;
	
	public GameWeldingSparks(int x, int y)
	{
		super(x, y, 204);
		this.x = x;
		this.y = y;
		rect = new Rectangle2D.Double();
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		for (Particle p : list)
		{
			p.draw(g);
		}
	}
	
	public void update(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
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
