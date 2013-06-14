package gameSteamCreation;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class WaterSteam extends GameObject
{
	
	private ArrayList<Particle> steam = new ArrayList<Particle>();
	
	public WaterSteam(int x, int y, int absolute)
	{
		super(x, y, absolute);
	}
	
	public WaterSteam()
	{
		super(200,650,GameObject.ABSOLUTE);
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		for(Particle p : steam)
		{
			p.draw(g);
		}
	}
	
	public ArrayList<Particle> getSteam()
	{
		return steam;
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