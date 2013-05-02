package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public class GameMenu extends GameMenuItem {
	
	private GameObject[] items;
	
	public int pointer;
	
	public boolean visible;
	
	public GameMenu(int x, int y, GameObject[] items)
	{
		visible = true;
		this.x = x;
		this.y = y;
		this.items = items;
		pointer = 0;
		update();
	}

	@Override
	public void draw(Graphics2D g) {
		if (visible)
			for (GameObject item : items)
				item.draw(g);

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
	

}
