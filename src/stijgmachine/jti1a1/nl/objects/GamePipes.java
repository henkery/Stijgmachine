package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;

import stijgmachine.jti1a1.nl.controller.Main;

public class GamePipes extends GameObject {
	private final int index; // haat aan dit attribuut
	private Point2D frontLocation, backLocation; // haat aan dit attribuut
	public static final int id = 777; 
	private int frontDirection, backDirection;  // haat aan dit attribuut

	private	Dimension size;
	private boolean moveable = true;
	

	/** 
	 * directions
	 * 0 = left 
	 * 1 = up
	 * 2 = right
	 * 3 = down
	 * **/
	
	public GamePipes(int x, int y, int index, Dimension size) {
		super(x, y, GameObject.ABSOLUTE);
		this.index = index;
		this.x = x;
		this.y = y;
		this.size = size;
		setDirections(index);
		
	}
	
	public GamePipes(Point2D point,int index, Dimension size){
		super((int)point.getX(),(int)point.getY(),GameObject.ABSOLUTE);
		setLocation(point);
		this.index = index;
		this.size = size;
		setDirections(index);
		System.out.println("index: " + index);
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		switch(index){
		case 0: //horizontal
			g.setColor(new Color((int)Math.random()*255, (int)Math.random()*255, (int)Math.random()*255));
			Rectangle2D rectH = new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
			g.fill(rectH);
			break;
		default:
		//case 1: // vertical
			Rectangle2D rectV = new Rectangle2D.Double(x, y, size.getWidth(), size.getHeight());
			g.fill(rectV);
			break;
		}
	}
	
	public void setDirections(int index){
		switch(index){
		case 0: this.frontDirection = 0;
				this.backDirection = 2;
			break;
		case 1: this.frontDirection = 1;
				this.backDirection = 3;
			break;
		case 2: this.frontDirection = 1;
				this.backDirection = 2;
			break;
		case 3: this.frontDirection = 2;
				this.backDirection = 3;
			break;
		case 4: this.frontDirection = 3;
				this.backDirection = 0;
			break;
		case 5: this.frontDirection = 0;
				this.backDirection = 1;
			break;
		}
	}
	
	public boolean getConnection(int frontDirOb1, int backDirOb1, int frontDirOb2, int backDirOb2){
		int ftDirOb1 = frontDirOb1;
		int bkDirOb1 = backDirOb1 + 2;
		
		int ftDirOb2 = frontDirOb2;
		int bkDirOb2 = backDirOb2 + 2;
		
		if (bkDirOb1 > 3 || bkDirOb2 > 3)
		{
			bkDirOb1 =- 4;
			bkDirOb2 =- 4;
		}
		if (ftDirOb1 == ftDirOb2 || ftDirOb1 == bkDirOb2 || bkDirOb1 == ftDirOb2 || bkDirOb1 == bkDirOb2)
			return true;
		
		return false;
	}
	
	public void getConnectLocation(int frontDirOb1, int backDirOb1, int frontDirOb2, int backDirOb2){
		
	}
	
	public int getFrontDirection(){
		return frontDirection;
	}
	public int getBackDirection(){
		return backDirection;
	}

	public Point2D getFrontLocation(){
		if (index == 0)
			frontLocation = new Point2D.Double (getLocation().getX(),getLocation().getY() + (size.getHeight()/2));
		if (index == 1)
			frontLocation = new Point2D.Double(getLocation().getX()+(size.getWidth()/2), getLocation().getY());
		if (index == 2)
			frontLocation = new Point2D.Double(getLocation().getX() + size.getWidth() + (size.getWidth()/2), getLocation().getY()+ (size.getHeight()/2));
		if (index == 3)
			frontLocation = new Point2D.Double(getLocation().getX() + (size.getWidth()),getLocation().getY() + (size.getHeight()/2));
		if (index == 4)
			frontLocation = new Point2D.Double(getLocation().getX() + (size.getWidth()/2),getLocation().getY() + size.getHeight());
		if (index == 5)
			frontLocation = new Point2D.Double(getLocation().getX(),getLocation().getY() + (size.getHeight()/2));
		return frontLocation;
	
	}
	
	public Point2D getBackLocation(){
		if (index == 0)
			backLocation = new Point2D.Double (getLocation().getX() + size.getWidth(),getLocation().getY() + (size.getHeight()/2));
		if (index == 1)
			backLocation = new Point2D.Double(getLocation().getX() + (size.getWidth()/2), getLocation().getY() + size.getHeight());
		if (index == 2)
			backLocation = new Point2D.Double(getLocation().getX() + size.getWidth(), getLocation().getY() + (size.getHeight()/2));
		if (index == 3)
			backLocation = new Point2D.Double(getLocation().getX() + (size.getWidth()/2),getLocation().getY() + size.getHeight());
		if (index == 4)
			backLocation = new Point2D.Double(getLocation().getX(),getLocation().getY() + (size.getHeight()/2));
		if (index == 5)
			backLocation = new Point2D.Double(getLocation().getX() + (size.getWidth()/2) ,getLocation().getY());
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
	
	public void setMoveable(boolean move){
		this.moveable = move;
	}
	
	public boolean getMoveAble(){
		return moveable;
	}
	
	public void update(int x, int y) {
		if (!moveable)
			return;
		this.x = x;
		this.y = y;
//		this.x = (int) ((Main.resX/1024.0f * x));
//		this.y = (int) ((Main.resY/768.0f * y));
		
	}

}