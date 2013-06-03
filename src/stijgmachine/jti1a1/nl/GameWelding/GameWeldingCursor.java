package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingCursor extends GameObject
{
	private int x;
	private int y;
	private BufferedImage img;
	private GameWeldingCursorDetector detector;
	
	public GameWeldingCursor(String d, int x, int y, BufferedImage img)
	{
		super(0, 0, GameObject.ABSOLUTE);
		this.x = x;
		this.y = y;
		this.img = img;
		detector = new GameWeldingCursorDetector(d, x, y);
	}

	@Override
	public void click()
	{
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		g.drawImage(img, null, x, y);
	}

	@Override
	public int getID()
	{
		return 0;
	}

	public void update(int x, int y)
	{
		this.x = Main.resX/1024*x;
		this.y = Main.resY/768*y;
		detector.update(x, y);
	}
	
	public GameWeldingCursorDetector getDetector()
	{
		return detector;
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
