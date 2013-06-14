package stijgmachine.jti1a1.nl.objects;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;

public class GameMineCart extends GameObject {

	private Image mineCartImage, railTrack;
	private Dimension size;
	
	
	public GameMineCart(int x, int y, int relativeTo, Dimension size) {
		super(x, 60, relativeTo);
		mineCartImage = new ImageIcon("./images/horPipe.png").getImage();
		railTrack = new ImageIcon("./images/railTrack.png").getImage();
		this.size = size;
	}

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		g.fillRect(x, y, (int) size.getWidth(), (int)size.getHeight());
		//		g.drawImage(railTrack, 0, 60, null);
//		g.drawImage(mineCartImage, x, y, null);
	}

	@Override
	public int getID() {
		return 0;
	}
	
	public Point2D getLocation(){
		Point2D location = new Point2D.Double (x,y);
		return location;
	}

	@Override
	public void click() {
		
	}
	public Dimension getSize(){
		return size;
	}
	
	public void move(){
		if (x < 1080-size.getWidth())
			x++;
		else
			x--;
	}

}
