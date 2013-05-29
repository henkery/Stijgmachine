package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingDoor extends GameObject
{
	private BufferedImage img;

	public GameWeldingDoor()
	{
		super(0, 0, 204);
		try
		{
			img = ImageIO.read(getClass().getResource("/res/rocket_door.png"));
		} catch (IOException e)
		{
		}
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		g.drawImage(img, null, 850, 65);
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
