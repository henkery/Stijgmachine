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
	
	public SteamGamePanel()
	{
	
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(!SteamGameControl.getGameDone()){
		// HET TEKENENEN VAN DE BASIS ACHTERGROND
		if(SteamGameControl.getGameStart()){
			g2.drawImage(SteamGameControl.getBackImgStart(), 0, 0, Main.resX, Main.resY, null);
			g2.drawImage(SteamGameControl.getLighter(),SteamGameModel.translatepixelX(1220) , SteamGameModel.translatepixelY(950), 20, 40, null);
		}
		// HET TEKENEN VAN DE KOLEN IN DE OPEN HAARD 
		if(SteamGameControl.getCoalMoved())
		{
			g2.drawImage(SteamGameControl.getCoalPileImage(), SteamGameModel.translatepixelX(867), SteamGameModel.translatepixelY(901), 192, 70, null);
		}
		// HET NIET DRAAIEN VAN DE HETE STOOM WIEL
		if(!SteamGameControl.getHeatWheelMoved())
			g2.drawImage(SteamGameControl.getHeatWheelImage(),SteamGameControl.translateX(780),SteamGameControl.translateY(520),50,50,null);
		// HET DRAAIEN VAN DE HETE STOOM WIEL
		if(SteamGameControl.getHeatWheelMoved())
		{
			if(SteamGameControl.getStopHeat() < 12)
				drawHeatWheel(g2);
			else
				g2.drawImage(SteamGameControl.getHeatWheelImage(),SteamGameControl.translateX(780),SteamGameControl.translateY(520),50,50,null);
		}
		if(!SteamGameControl.getSteamWheelMoved())
			g2.drawImage(SteamGameControl.getHeatWheelImage(), SteamGameControl.translateX(700), SteamGameControl.translateY(325), 80, 80, null);
		if(SteamGameControl.getSteamWheelMoved())
		{
			if(SteamGameControl.getStopSteam() < 12 && SteamGameControl.getStopHeat() >= 12)
				drawWheel(g2);
			else
				g2.drawImage(SteamGameControl.getHeatWheelImage(), SteamGameControl.translateX(700), SteamGameControl.translateY(325), 80, 80, null);
		}
		// HET BRANDEN VAN DE KOLEN
		if(SteamGameControl.getCoalLit())
		{
			g2.drawImage(SteamGameControl.getFire(), SteamGameControl.translateX(880), SteamGameControl.translateY(830), 80, 120,null);
			g2.drawImage(SteamGameControl.getFire(), SteamGameControl.translateX(950), SteamGameControl.translateY(840), 70, 110,null);
			g2.drawImage(SteamGameControl.getFire(), SteamGameControl.translateX(900), SteamGameControl.translateY(800), 100, 150,null);
		}
		// HET BRANDEN VAN HET RODE LICHT
		if(SteamGameControl.getCounter() <= 8){
			g2.drawImage(SteamGameControl.getRedWarning(), SteamGameControl.translateX(740), SteamGameControl.translateY(220), 102,60, null);
		}
		// HET BRANDEN VAN HET GROENE LICHT
		if(SteamGameControl.getCounter() == 8 && SteamGameControl.getStopSteam() == 12){
			g2.drawImage(SteamGameControl.getGreenWarning(),SteamGameControl.translateX(740),SteamGameControl.translateY(220),102,60,null);
			SteamGameControl.setCount(9);
		}
		drawObjects(Main.getObjects(), g2);
		if(SteamGameControl.getCounter() == 9)
			SteamGameControl.setGameDone(true);
		}
	}

	
	public void drawWheel(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(SteamGameControl.getWheelImg(), SteamGameControl.translateX(700), SteamGameControl.translateY(325), 80, 80, null);
		SteamGameControl.updateStopSteam(1);
	}
	
	public void drawHeatWheel(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(SteamGameControl.getWheelImg(), SteamGameControl.translateX(780), SteamGameControl.translateY(520), 50, 50, null);
		SteamGameControl.updateStopHeat(1);
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}

