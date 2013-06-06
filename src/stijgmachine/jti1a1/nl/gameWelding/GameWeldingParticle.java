package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.plaf.ColorUIResource;

public class GameWeldingParticle
{
	public double x, y;
	private double t = 0;
	private double speed;
	private double angle;

	private Color a = new Color(238, 233, 233);
//	private Color b = new Color(205, 201, 201);
//	private Color c = new Color(139, 137, 137);
		
	public float alpha;

	public GameWeldingParticle()
	{
		speed = Math.random() * 200.0;
		angle = Math.random() * 60 + 60.0;
		alpha = 0.80f;
	}
	
	public void update()
	{
		x = speed * Math.cos((Math.PI / 180) * angle) * t;
		y = speed * Math.sin((Math.PI / 180) * angle) - 0.5 * 9.8 * Math.pow(t, 2.0);

		t += 0.05;
		alpha -= 0.005f;
		alpha = Math.max(0, alpha);
	}

	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, 50, 50);

		g2.setColor(new ColorUIResource(a));
		g2.fill(ellipse);
	}
}

