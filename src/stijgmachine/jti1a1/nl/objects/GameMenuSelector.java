package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public class GameMenuSelector extends GameMenuItem {
	
	public int select;
	public String[] items;

	public GameMenuSelector(String[] items, int x, int y)
	{
		select = 0; 
		this.items = items;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawString("< " + items[select] + " >", x, y);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	public void goRight()
	{
		select++;
		if (select >= items.length)
			select = 0;
	}

	public void goLeft() {
		select--;
		if (select < 0)
			select = items.length-1;
		
	}

}
