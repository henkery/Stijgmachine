package stijgmachine.jti1a1.nl.GameWelding;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.view.MiniGameView;

public class GameWeldingView extends MiniGameView
{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private BufferedImage backgroundLeft;
	
	public GameWeldingView()
	{
		super();
		try
		{
			background = ImageIO.read(getClass().getResource("/res/machine_inside_empty.png"));
		} catch (IOException e)
		{
		}
		try
		{
			backgroundLeft = ImageIO.read(getClass().getResource("/res/machine_inside_left.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getID()
	{
		return MiniGameView.GAMEWELDING;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;	
		g2.drawImage(background, null, 183, 0);
		g2.drawImage(backgroundLeft, null, 0, 0);
		drawObjects(Main.getObjects(), g2);
	}

}
