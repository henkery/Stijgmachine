package stijgmachine.jti1a1.nl.startScreenStory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import stijgmachine.jti1a1.nl.view.MiniGameView;

public class StartScreenStoryView extends MiniGameView 
{
	private static final long serialVersionUID = 4153138609401176035L;
	private static BufferedImage background;
	

	public StartScreenStoryView() throws IOException, InterruptedException
	{		
	}
	
	public static void setBackground(BufferedImage b)
	{
		background = b;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;	
		g2.drawImage(background, null, 0, 0);
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}



}
