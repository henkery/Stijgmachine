package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public class GameMenuSelector extends GameMenuItem {
	
	public int select;
	public String[] items;

	public GameMenuSelector(String[] items, int x, int y, int relativeTo)
	{
		super(x, y, relativeTo);
		select = 0; 
		this.items = items;
	}
	
	@Override
	public void draw(Graphics2D g, int h, int w, int x2, int y2) {
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

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

}
