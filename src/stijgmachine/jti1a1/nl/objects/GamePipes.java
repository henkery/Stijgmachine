package stijgmachine.jti1a1.nl.objects;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GamePipes extends GameObject {
	private int index;
	private Point2D backLocation;
	public static final int id = 777;
	
	Dimension size;
	public GamePipes(int x, int y, int index, Dimension size) {
		super(x, y, GameObject.ABSOLUTE);
		this.index = index;
		this.x = x;
		this.y = y;
		this.size = size;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		switch(index){
		case 0: //horizontal
			Rectangle2D rectH = new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
			g.draw(rectH);
			break;
		case 1: // vertical
			Rectangle2D rectV = new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
			g.draw(rectV);
			break;
		case 2://
//			Rectangle2D re
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}

	public Point2D getBackLocation(){
		if (index == 0)
			backLocation = new Point2D.Double (getLocation().getX()- size.getWidth(),getLocation().getY());
		if (index == 1)
			backLocation = new Point2D.Double(getLocation().getX(), getLocation().getY() - size.getHeight());
		return backLocation;
	}
	
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	// if object pressed click methode
	public void click() {
		// TODO Auto-generated method stub
		
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setLocation(Point2D point){
		this.x = (int)point.getX();
		this.y = (int)point.getY();
	}
	
	public Point2D getLocation(){
		Point2D location = new Point2D.Double(x, y); 
		return location;
	}
	
	public void update(int x, int y) {
			this.x = x;
			this.y = y;
	}

}
