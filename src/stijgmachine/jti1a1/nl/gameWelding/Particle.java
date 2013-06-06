package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.plaf.ColorUIResource;

public class Particle
{
	private double x = 1380, y = 250;
	private double t = 0;
	private double speed;
	private double angle;
	private double size;
	private float alpha;
	
	public Particle(String s)
	{
		if(s == "steam")
		{
			speed = Math.random() * 200.0;
			angle = Math.random() * 60 + 60.0;
			alpha = 0.40f;
			size = Math.random() * 100;
		}
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
		x = (speed * Math.cos((Math.PI / 180) * angle) * t) +1380;
		y = (speed * Math.sin((Math.PI / 180) * angle) - 1 * 9.8 * Math.pow(t, 3.0)) + 250;
		t += 0.05;
		alpha -= 0.005f;
		alpha = Math.max(0, alpha);
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, size, size);
		
		g2.setColor(new Color(238, 233, 233, 25));
		g2.fill(ellipse);
	}
}