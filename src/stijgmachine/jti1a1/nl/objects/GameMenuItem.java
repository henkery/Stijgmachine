package stijgmachine.jti1a1.nl.objects;

import java.awt.Graphics2D;

public abstract class GameMenuItem extends GameObject {

	public GameMenuItem(int x, int y, int relativeTo) {
		super(x, y, relativeTo);
		// TODO Auto-generated constructor stub
	}

	protected boolean pointed;

	public void unsetPointed() {
		pointed = false;
		
	}
	
	public void setPointed() {
		pointed = true;
		//System.out.println("got pointed");
	}
}
