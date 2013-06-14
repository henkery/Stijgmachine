package gameSteamCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Particle
{	
	private double x, y;
	private double t = 0;
	private double speed;
	private double angle;
	private double steamSize;
	private float alpha;
	
	public Particle()
	{
//		if(SteamGameControl.getCounter() >= 6)
//		{
			this.x = randomWithRange();
			this.y = 650;
			speed = Math.random()*20.0;
			angle = Math.random()*180+30.0;
			alpha = 0.80f;
			steamSize = Math.random()*70;
//		}
		
	}
	
	public int randomWithRange()
    {
       int range = (350 - 250) + 1;
       return (int)(Math.random() * range) + 250;
    }
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void update()
	{
		x = (speed * Math.cos((Math.PI / 180) * angle) * t) +300;
		y = (speed * Math.sin((Math.PI / 180) * angle) - 1 * 0.8 * Math.pow(t, 3.0)) + 650;
		t += 0.05;
		alpha -= 0.005f;
		alpha = Math.max(0, alpha);
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		Ellipse2D.Double ellipse = new Ellipse2D.Double(x,y,steamSize,steamSize);
		
		g2.setColor(new Color(238,233,233,25));
		//g2.setColor(Color.white);
		g2.fill(ellipse);
	}
}