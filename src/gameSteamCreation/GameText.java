package gameSteamCreation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameText extends GameObject
{
	private String content;
	private int fontsize;
	
	public GameText(int x, int y, int relativeTo)
	{
		super(x, y, relativeTo);
		content = "Goed gedaan, op naar het volgende onderdeel!";
		fontsize = 200;
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		
		Font oldfont = g.getFont();
		g.setFont(oldfont.deriveFont(fontsize));
		g.setColor(Color.green);
		if(SteamGameControl.getCounter()==10)
		g.drawString(content, 600, 400);
		//System.out.println(l/2 + " " + w/2);
		g.setFont(oldfont);
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
