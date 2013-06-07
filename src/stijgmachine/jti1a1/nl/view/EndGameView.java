package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import stijgmachine.jti1a1.nl.controller.Main;

public class EndGameView extends MiniGameView {

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.clearRect(0, 0, Main.resX, Main.resY);
		drawObjects(Main.getObjects(), g2);
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
