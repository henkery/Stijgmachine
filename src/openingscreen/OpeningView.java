package openingscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.view.MiniGameView;

public class OpeningView extends MiniGameView
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private int ticks,x,y,alpha;
	
	public OpeningView()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(ticks < 15)
			ticks++; 
		else
		{
			ticks = 0;
			x = (int)(0+Math.random()*2)-100;
			y = (int)(0+Math.random()*2)-100;
		}
		alpha = (int)(0+Math.random()*5)+150;
		g2.drawImage(OpeningModel.getBeginScreen(),x,y,Main.resX+100,Main.resY+100, null);
		g2.drawImage(OpeningModel.getButtonA(),OpeningModel.translatepixelX(700),OpeningModel.translatepixelY(450),OpeningModel.translatepixelX(150),OpeningModel.translatepixelY(150),null);
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, Main.resX, Main.resY);
//		g2.setColor(new Color(139,105,20,alpha));
		g2.setColor(new Color(139,69,19,alpha));
		g2.fill(rect);
		g2.draw(rect);
		drawObjects(Main.getObjects(), g2);
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
