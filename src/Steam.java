import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.plaf.ColorUIResource;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class Steam extends GameObject
{
	private ArrayList<Particle> list = new ArrayList<Particle>();


	public Steam()
	{
		super(1420, 250, 204);
	}
	
	public ArrayList<Particle> getList()
	{
		return list;
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		for (Particle p : list)
		{
			p.draw(g);
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
	}
	
	
}