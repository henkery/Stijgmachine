package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameObjectContainer extends GameObject {
	
	private int h, w;
	private ArrayList<GameObject> objects;
	private boolean border;

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		int[] loc = getPosition(x, y, x2, y2, h, w, relativeTo);

		if (border)
		{
			g.draw(new Rectangle2D.Double(loc[0],loc[1],h,w));
		}
		for (GameObject item : objects)
		{
			item.draw(g, h, w, loc[0], loc[1]);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public GameObjectContainer(int x, int y, int h, int w, int relativeTo)
	{
		super(x, y, relativeTo);
		this.h = h;
		this.w = w;
		objects = new ArrayList<GameObject>();
		border = true;
	}
	
	
	public GameObjectContainer(int x, int y, int h, int w, int relativeTo, GameObject[] gameObjects) {
		super(x, y, relativeTo);
		this.h = h;
		this.w = w;
		objects = new ArrayList<GameObject>();
		for (GameObject item : gameObjects)
			objects.add(item);
		border = true;
	}

	public void addObject(GameObject object)
	{
		objects.add(object);
	}
	
	public void removeObject(int i)
	{
		objects.remove(i);
	}
	
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

}
