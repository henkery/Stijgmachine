package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public class GameMenu extends GameMenuItem {
	
	private GameObject[] items;
	
	public int pointer;
	
	public boolean visible;
	
	public GameMenu(GameObject[] items, int x, int y, int relativeTo)
	{
		super(x,y,relativeTo);
		visible = true;
		this.items = items;
		pointer = 0;
		update();
	}

	@Override
	public void draw(Graphics2D g, int h, int w, int x2, int y2) {
		if (visible)
			for (GameObject item : items)
				item.draw(g, h, w, x2, y2);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void update()
	{
		for (GameObject item : items)
		{
			 ((GameMenuItem) item).unsetPointed();
		}
	//	if (point < buttons.size() && point >= 0)
		((GameMenuItem) items[pointer]).setPointed();
	}
	
	public void itemUp()
	{
		pointer--;
		if (pointer < 0)
			pointer = items.length-1;
		update();
	}
	
	public void itemDown()
	{
		pointer++;
		if (pointer >= items.length)
			pointer = 0;
		update();
	}
	
	public void itemRight()
	{
		if (items[pointer].getID() == 5)
			((GameMenuSelector) items[pointer]).goRight();
		update();
	}
	
	public void itemLeft()
	{
		if (items[pointer].getID() == 5)
			((GameMenuSelector) items[pointer]).goLeft();
		update();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public void click() {
		items[pointer].click();
		
	}

}
