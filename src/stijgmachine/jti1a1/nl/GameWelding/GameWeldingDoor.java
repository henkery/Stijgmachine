package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class GameWeldingDoor extends GameObject
{
	private BufferedImage img;
	private ArrayList<Point> list;

	public GameWeldingDoor()
	{
		super(0, 0, 204);
		try
		{
			img = ImageIO.read(getClass().getResource("/res/rocket_door.png"));
		} catch (IOException e)
		{
		}
		list = new ArrayList<Point>();
		list.add(new Point(1038, 528));
		list.add(new Point(1031, 613));
		list.add(new Point(1056, 595));
		list.add(new Point(1108, 584));
		list.add(new Point(1147, 592));
		list.add(new Point(1175, 623));
		list.add(new Point(1137, 704));
		list.add(new Point(1082, 772));
		list.add(new Point(1091, 798));
		list.add(new Point(1161, 816));
		list.add(new Point(1212, 805));
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2)
	{
		g.drawImage(img, null, 850, 65);
		for(Point p  : list)
		{
			g.drawOval((int)p.getX(), (int)p.getY(), 10, 10);
		}
	}
	
	public ArrayList<Point> getList()
	{
		return list;
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
	
	public class Point extends Point2D
	{
		double x;
		double y;
		
		public Point(double x, double y)
		{
			setLocation(x, y);
		}
		
		@Override
		public double getX()
		{
			// TODO Auto-generated method stub
			return x;
		}

		@Override
		public double getY()
		{
			// TODO Auto-generated method stub
			return y;
		}

		@Override
		public void setLocation(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
	}

}
