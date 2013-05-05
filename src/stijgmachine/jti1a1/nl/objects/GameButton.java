package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GameButton extends GameMenuItem {
	
	private int l, w;
	private float fontsize;
	private String content;

	public GameButton(int x, int y, int relativeTo, int l, int w, String string) {
		super(x,y,relativeTo);
		this.l = l;
		this.w = w;
		this.fontsize = 12;
		content = string;
		pointed = false;
	}
	
	public GameButton(int x, int y, int relativeTo, int l, int w, int fontsize, String string) {
		super(x,y,relativeTo);
		this.l = l;
		this.w = w;
		this.fontsize = (float)fontsize;
		content = string;
		pointed = false;
	}

	@Override
	public void draw(Graphics2D g, int h, int wM, int x2, int y2) {
		Graphics2D g2 = g;
		int[] loc = getPosition(x, y, x2, y2, h, wM, relativeTo);
		if (pointed)
			g2.setColor(Color.red);
		else
			g2.setColor(Color.black);
		g2.fill(new Rectangle2D.Double(loc[0], loc[1], l, w));
		g2.setColor(Color.white);
		Font oldfont = g2.getFont();
		g2.setFont(oldfont.deriveFont(fontsize));
		g2.drawString(content, loc[0]-((content.length()/2)), loc[1]+(w/2));
		//System.out.println(l/2 + " " + w/2);
		g2.setFont(oldfont);

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

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	
}
