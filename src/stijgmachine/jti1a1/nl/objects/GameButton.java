package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GameButton extends GameObject {
	
	private int l, w;
	private String content;
	private boolean pointed;

	public GameButton(int x, int y, int l, int w, String string) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		content = string;
		pointed = false;
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = g;
		if (pointed)
			g2.setColor(Color.red);
		else
			g2.setColor(Color.black);
		g2.fill(new Rectangle2D.Double(x, y, l, w));
		g2.setColor(Color.white);
		g2.drawString(content, x, y+10);

	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameButton.BUTTON;
	}

	
	public boolean isClicked(int x, int y)	{
		return (x > this.x && y > this.y) && (x < this.x + w && y < this.y + l);
	}

	public void unsetPointed() {
		pointed = false;
		
	}
	
	public void setPointed() {
		pointed = true;
		//System.out.println("got pointed");
	}
}
