package gameSteamCreation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.view.MiniGameView;

public class SteamGamePanel extends MiniGameView
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public boolean coalLit = false;
	int screenWidth;
	int screenHeight;
	Image fire = null;
	
	public SteamGamePanel()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int)screenSize.getWidth();
		screenHeight = (int)screenSize.getHeight();
	
		fire = Toolkit.getDefaultToolkit().createImage("images/animatedfire.gif");
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// HET TEKENENEN VAN DE BASIS ACHTERGROND
		if(SteamGameControl.getGameStart()){
			g2.drawImage(SteamGameControl.getBackImgStart(), 0, 0, screenWidth, screenHeight, null);
			g2.drawImage(SteamGameControl.getLighter(),1000 , 780, 20, 40, null);
		}
		// HET TEKENEN VAN DE KOLEN IN DE OPEN HAARD 
		if(SteamGameControl.getCoalMoved())
		{
			g2.drawImage(SteamGameControl.getCoalPileImage(), 707, 751, 192, 70, null);
		}
		// HET NIET DRAAIEN VAN DE HETE STOOM WIEL
		if(!SteamGameControl.getHeatWheelMoved())
			g2.drawImage(SteamGameControl.getHeatWheelImage(),640,430,50,50,null);
		// HET DRAAIEN VAN DE HETE STOOM WIEL
		if(SteamGameControl.getHeatWheelMoved())
		{
			if(SteamGameControl.getStopHeat() < 12)
				drawHeatWheel(g2);
			else
				g2.drawImage(SteamGameControl.getHeatWheelImage(),640,430,50,50,null);
		}
		if(!SteamGameControl.getSteamWheelMoved())
			g2.drawImage(SteamGameControl.getHeatWheelImage(), 580, 265, 80, 80, null);
		if(SteamGameControl.getSteamWheelMoved())
		{
			if(SteamGameControl.getStopSteam() < 12 && SteamGameControl.getStopHeat() >= 12)
				drawWheel(g2);
			else
				g2.drawImage(SteamGameControl.getHeatWheelImage(), 580, 265, 80, 80, null);
		}
		// HET BRANDEN VAN DE KOLEN
		if(SteamGameControl.getCoalLit())
		{
			g2.drawImage(fire, 730, 680, 80, 120,null);
			g2.drawImage(fire, 800, 690, 70, 110,null);
			g2.drawImage(fire, 750, 650, 100, 150,null);
		}
		// HET BRANDEN VAN HET RODE LICHT
		if(SteamGameControl.getCounter() <= 8){
			g2.drawImage(SteamGameControl.getRedWarning(), 600, 120, 102,60, null);
		}
		// HET BRANDEN VAN HET GROENE LICHT
		if(SteamGameControl.getCounter() == 8 && SteamGameControl.getStopSteam() == 12){
			g2.drawImage(SteamGameControl.getGreenWarning(),600,120,102,60,null);
		}
		drawObjects(Main.getObjects(), g2);
	}

	
	public void drawWheel(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(SteamGameControl.getWheelImg(), 580, 265, 80, 80, null);
		SteamGameControl.updateStopSteam(1);
	}
	
	public void drawHeatWheel(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(SteamGameControl.getWheelImg(), 640, 430, 50, 50, null);
		SteamGameControl.updateStopHeat(1);
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}

