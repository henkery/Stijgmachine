package gameSteamCreation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class Cursor extends GameObject
{
	private Image cursor;
	private Image shovel;
	private Image shovelWithCoal;
	private Image matches;
	private Image hand;

	public Cursor(int x, int y, int relativeTo)
	{
		super(x, y, relativeTo);
		cursor = Toolkit.getDefaultToolkit().createImage("images/cursor.png");
		shovel = Toolkit.getDefaultToolkit().createImage("images/shovelImg.png");
		shovelWithCoal = Toolkit.getDefaultToolkit().createImage("images/shovelWithCoalImg.png");
		matches = Toolkit.getDefaultToolkit().createImage("images/lighterImg.png");
		hand = Toolkit.getDefaultToolkit().createImage("images/userHandImg.png");
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{	
		if(SteamGameControl.getCounter() == 0 || SteamGameControl.getCounter() == 3)
			g.drawImage(cursor, x, y, 50, 50, null);
		else if(SteamGameControl.getCounter()==1)
			g.drawImage(shovel, x-250, y-50, 250, 50, null);
		else if(SteamGameControl.getCounter()==2)
			g.drawImage(shovelWithCoal,x-250,y-50,250,50,null);
		else if(SteamGameControl.getCounter()==4 || SteamGameControl.getCounter() == 5)
			g.drawImage(matches, x, y, 30, 60, null);
		else if( SteamGameControl.getCounter() >= 6 )
			g.drawImage(hand,x,y,30,50,null);
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
	
	public void update(int x, int y) {
		this.x = (int) (Main.resX/1024f*x);
		this.y = (int) (Main.resY/768f*y);
	}
}
