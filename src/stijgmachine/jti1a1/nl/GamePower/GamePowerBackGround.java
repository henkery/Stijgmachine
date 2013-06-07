package stijgmachine.jti1a1.nl.GamePower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import stijgmachine.jti1a1.nl.objects.GameLabel;

public class GamePowerBackGround extends GameLabel 
{
	private Point p1 = new Point();
	public GamePowerBackGround(int x, int y, int relativeTo, String string,
			int fontsize) {
		super(x, y, relativeTo, string, fontsize);
		// TODO Auto-generated constructor stub
	}
	
	
	public void updateCursorPos(Point p1)
	{
		this.p1 = p1;
	}
	
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		// TODO Auto-generated method stub
		AffineTransform tx = new AffineTransform();
		ImageIcon image = new ImageIcon("src/factBack1.jpg");
//		ImageIcon image2 = new ImageIcon("src/SteamPipe1.png");
		ImageIcon image4 = new ImageIcon("src/energy-holder.png");
		ImageIcon image5 = new ImageIcon("src/cursor.png");
//		ImageIcon image3 = new ImageIcon("src/CogWall4.png");
		tx.translate(0,0);
		g.drawImage(image.getImage(),tx,null);
		tx.translate(550, 450);
//		g.drawImage(image2.getImage(),tx,null);
		g.drawImage(image4.getImage(),tx,null);
		tx.translate(-550,-450);
//		tx.translate(400,0);
//		g.drawImage(image3.getImage(),tx,null);
		tx.translate(p1.x, p1.y);
		g.drawImage(image5.getImage(),tx,null);
		g.setColor(new Color(95,70,46));
	}

}
