package stijgmachine.jti1a1.nl.objects;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GamePipes extends GameObject {
	private int ID, xPosition,yPosition;
	Dimension size;
	public GamePipes(int x, int y, int id,int xPosition,int yPosition, Dimension size) {
		super(x, y, GameObject.ABSOLUTE);
		this.ID = id;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.size = size;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		switch(getID()){
		case 0: //horizontal
			Rectangle2D rect = new Rectangle2D.Double(xPosition, yPosition, size.getWidth(), size.getHeight());
			g.draw(rect);
			break;
		case 1: // vertical
			g.drawLine(xPosition/2,yPosition/2,(int)size.getWidth()/2,(int)size.getHeight()/2);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}
	public void update(int x, int y) {
			this.x = x;
			this.y = y;
	}

}
