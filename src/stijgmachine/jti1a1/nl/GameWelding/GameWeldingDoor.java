package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingDoor extends GameObject
{
	private BufferedImage img;
	private ArrayList<Rectangle2D> pointList;
	private Rectangle2D weldingArea;

	public GameWeldingDoor()
	{
		super(0, 0, 204);
		try
		{
			img = ImageIO.read(getClass().getResource("/res/rocket_door.png"));
		} catch (IOException e)
		{
		}
		setPoints();
		weldingArea = new Rectangle2D.Double(1000, 260, 245, 615);
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		g.drawImage(img, null, 850, 65);		
	}
	
	public Rectangle2D getWeldingArea()
	{
		return weldingArea;
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
	
	public void setPoints()
	{
		pointList = null;
		pointList = new ArrayList<Rectangle2D>();
		pointList.add(new Rectangle2D.Double(1030, 520, 20, 20));
		pointList.add(new Rectangle2D.Double(1020, 610, 20, 20));
		pointList.add(new Rectangle2D.Double(1056, 585, 20, 20));
		pointList.add(new Rectangle2D.Double(1105, 580, 20, 20));
		pointList.add(new Rectangle2D.Double(1147, 592, 20, 20));
		pointList.add(new Rectangle2D.Double(1160, 623, 20, 20));
		pointList.add(new Rectangle2D.Double(1125, 704, 20, 20));
		pointList.add(new Rectangle2D.Double(1070, 760, 20, 20));
		pointList.add(new Rectangle2D.Double(1091, 798, 20, 20));
		pointList.add(new Rectangle2D.Double(1155, 805, 20, 20));
		pointList.add(new Rectangle2D.Double(1185, 790, 20, 20));
	}
	
	public ArrayList<Rectangle2D> getPoints()
	{
		return pointList;
	}
	
}
