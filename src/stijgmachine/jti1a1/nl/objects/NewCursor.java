package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gameSteamCreation.Cursor;

public class NewCursor extends GameCursor {
	
	private Image cursorImage;
	
	public NewCursor() {
		//super(0, 0, GameObject.ABSOLUTE);
		cursorImage = new ImageIcon("./images/gameCursor.png").getImage();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.setColor(Color.BLACK);
		g.fillRect(x + 18, 0, 12, y-32);
		g.drawLine(x + 16, y, x+ 16, 0);
		g.drawImage(cursorImage, x,y-32,null);
	//	g.setColor(Color.BLUE);
	//	g.draw(new Rectangle2D.Double(x,y,10,10));
	}
}
