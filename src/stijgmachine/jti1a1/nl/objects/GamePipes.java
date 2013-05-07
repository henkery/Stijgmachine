package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public class GamePipes extends GameObject {
	private int ID;
	public GamePipes(int x, int y, int id) {
		super(x, y, GameObject.ABSOLUTE);
		this.ID = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		switch(getID()){
		case 0:
			g.drawLine(10, 10, 20, 20);
			break;
		case 1:
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
