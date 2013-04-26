package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class GameObject {
	
	public static final int BUTTON = 1;
	
	public int x;
	public int y;

	public abstract void draw(Graphics2D g);
	
	public abstract int getID();

}
