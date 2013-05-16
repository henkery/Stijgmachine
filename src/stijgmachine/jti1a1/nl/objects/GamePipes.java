package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GamePipes extends GameObject {
	private int index;
	
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
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		g.setColor(Color.BLACK);
		g.drawLine(width/2, height/2, width - width/2,  height-height/2);
	}

	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
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
