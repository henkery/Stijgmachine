package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GamePipes extends GameObject {
	private final int index; 
	private Point2D frontLocation, backLocation;
	public static final int id = 777; 
	private int frontDirection, backDirection;  
	private Image pipeImage;
	
	
	private	Dimension size;
	private boolean moveable = true;
	private int ftDirOb1 ;
	private int bkDirOb1 ;
	private int ftDirOb2 ;
	private int bkDirOb2 ;
	private int idPipe;
	private boolean yUp = true;
	

	/** 
	 * directions
	 * 0 = left 
	 * 1 = up
	 * 2 = right
	 * 3 = down
	 * **/
	
	public GamePipes(int x, int y, int index,int idPipe, Dimension size) {
		super(x, y, GameObject.ABSOLUTE);
		this.index = index;
		this.x = x;
		this.y = y;
		this.size = size;
		this.idPipe = idPipe;
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
		switch(index){
		case 0: //horizontal
			pipeImage = new ImageIcon("./images/horPipe.png").getImage();
			g.drawImage(pipeImage,x,y,null);
			break;
		default:
		case 1: // vertical
			pipeImage = new ImageIcon("./images/vertPipe.png").getImage();
			break;
		case 2: // up right
			pipeImage = new ImageIcon("./images/upRightPipe.png").getImage();
			break;
		case 3://right down
			pipeImage = new ImageIcon("./images/rightDownPipe.png").getImage();
			break;
		case 4: //down left
			pipeImage = new ImageIcon("./images/leftDownPipe.png").getImage();
			break;
		case 5: // left up
			pipeImage = new ImageIcon("./images/leftUpPipe.png").getImage();
			break;
		}
		g.drawImage(pipeImage,x,y,null);
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
		this.ftDirOb1 = frontDirOb1;
		this.bkDirOb1 = backDirOb1 + 2;
		
		this.ftDirOb2 = frontDirOb2;
		this.bkDirOb2 = backDirOb2 + 2;
		
		if (bkDirOb1 > 3 || bkDirOb2 > 3)
		{
			bkDirOb1 =- 4;
			bkDirOb2 =- 4;
		}
		if (ftDirOb1 == ftDirOb2 || ftDirOb1 == bkDirOb2 || bkDirOb1 == ftDirOb2 || bkDirOb1 == bkDirOb2)
			return true;
		
		return false;
	}
	
	public Point2D getConnectLocation(int frontDirOb1, int backDirOb1, int frontDirOb2, int backDirOb2){

			
			
			
			
			
			
		return new Point2D.Double(frontDirOb2, backDirOb2);
	}
	
	public int getFrontDirection(){
		return frontDirection;
	}
	public int getBackDirection(){
		return backDirection;
	}

	public Point2D getFrontLocation(){
		if (index == 0)
//			frontLocation = new Point2D.Double (getLocation().getX(),getLocation().getY() + (size.getHeight()/2));
			return new Point(super.x,super.y);
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
		return id;
	}

	@Override
	// if object pressed click methode
	public void click() {
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
	public int getIdPipe(){
		return idPipe;
	}
	
	public void update(int x, int y) {
		if (!moveable)
			return;
		this.x = x;
		this.y = y;
//		this.x = (int) ((Main.resX/1024.0f * x));
//		this.y = (int) ((Main.resY/768.0f * y));
		
	}
	
	private void moveUp(){
		y--;
	}
	
	private void moveDown(){
		y++;
	}
	
	public void move(){
		System.out.println(yUp);
		if (yUp){
			if (y <= 900 && y >= 850)
				moveUp();
			if (y <= 850)
				yUp = false;
			}
		if (!yUp){
			if (y <= 900 && y >= 850)
				moveDown();
			if (y >= 900)
				yUp = true;
		}		
	}	
}