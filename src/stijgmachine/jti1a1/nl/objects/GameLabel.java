package stijgmachine.jti1a1.nl.objects;

import java.awt.Font;
import java.awt.Graphics2D;

public class GameLabel extends GameObject {

	private String content;
	private int fontsize;

	public GameLabel(int x, int y, int relativeTo, String string, int fontsize) {
		super(x, y, relativeTo);
		this.content = string;
		this.fontsize = fontsize;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		Font oldfont = g.getFont();
		g.setFont(oldfont.deriveFont(fontsize));
		g.drawString(content, x, y);
		//System.out.println(l/2 + " " + w/2);
		g.setFont(oldfont);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
