package openingscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class StartText extends GameObject
{
	private String start;
	private int x,y;
	
	public StartText(int x, int y, int relativeTo)
	{
		super(x, y, relativeTo);
		this.x = x;
		this.y = y;
		start = "Druk op 'A' om te beginnen"; 
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		Font font = new Font(start, ABSOLUTE, 50);
		g.setFont(font);
		g.setColor(Color.orange);
		g.drawString(start, getX(), getY());
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
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}
