package stijgmachine.jti1a1.nl.GamePower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import stijgmachine.jti1a1.nl.objects.GameLabel;

public class GamePowerEndText extends GameLabel
{
	private String fontType;
	private String words;
	private int x;
	private int y;
	private int fontSize;
	
	public GamePowerEndText(int x, int y, int relativeTo, String string,
			int fontsize,String fontType) {
		super(x, y, relativeTo, string, fontsize);
		words = string;
		this.fontType = fontType;
		this.x=x;
		this.y=y;
		fontSize = fontsize;
		// TODO Auto-generated constructor stub
	}
		
	   private void drawString(Graphics2D g, String text, int x, int y) {
	        for (String line : text.split("\n"))
	            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	    }
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		Font oldfont = g.getFont();
		Font font = new Font(fontType,Font.BOLD,fontSize);
		g.setColor(Color.GREEN);
		g.setFont(font);
		drawString(g,words, (int)x, (int)y);
		//System.out.println(l/2 + " " + w/2);
		g.setFont(oldfont);
	}

}
