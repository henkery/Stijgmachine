package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Graphics2D;
import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingSteamParticles extends GameObject
{
	public ArrayList<GameWeldingParticle> steamList = new ArrayList<GameWeldingParticle>();

	public GameWeldingSteamParticles()
	{
		super(0, 0, 204);
		for (int i = 0; i < 2; i++)
		{
			steamList.add(new GameWeldingParticle());
		}
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		for (GameWeldingParticle p : steamList)
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
		// TODO Auto-generated method stub
		
	}
}
