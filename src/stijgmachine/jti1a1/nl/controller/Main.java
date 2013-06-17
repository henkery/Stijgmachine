package stijgmachine.jti1a1.nl.controller;

import gameSteamCreation.SteamGameModel;
import gameSteamCreation.SteamGamePanel;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import stijgmachine.jti1a1.nl.gameWelding.GameWeldingLogic;
import stijgmachine.jti1a1.nl.gameWelding.GameWeldingView;
import stijgmachine.jti1a1.nl.model.EndGameLogic;
import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import openingscreen.OpeningModel;
import openingscreen.OpeningView;


import stijgmachine.jti1a1.nl.GamePower.GamePowerLogic;
import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GameSoundtrack;
import stijgmachine.jti1a1.nl.startScreenStory.StartScreenStoryLogic;
import stijgmachine.jti1a1.nl.startScreenStory.StartScreenStoryView;
import stijgmachine.jti1a1.nl.view.EndGameView;
import stijgmachine.jti1a1.nl.view.MiniGameView;
import stijgmachine.jti1a1.nl.view.TestView;
import stijgmachine.jti1a1.nl.view.WaterSupplyView;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

public class Main {

	/**
	 * @param args
	 */
	
	private static MiniGameLogic logicSlot;
	private static MiniGameView viewSlot;
	private static JFrame frame;
	private Wiimote[] wiimotes;
	private static boolean fullscreen;
	public static int resX = 1920; 
	public static int resY = 1080; 
	public Object[][] list;
	private int i;
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException
	{
		new Main();
	}
	
	public Main() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException
	{
		list = new Object[][]{
				new Object[]{new OpeningModel(), new OpeningView()},
				new Object[]{new StartScreenStoryLogic(), new StartScreenStoryView()},//starts to early, suggestion. wait for first tick
				new Object[]{new AssemblyLineLogic(), new TestView()},
				new Object[]{new GamePowerLogic(), new TestView()},
				new Object[]{new AssemblyLineLogic(), new TestView()},
				new Object[]{new WaterSupplyGame(), new WaterSupplyView()}, //not working
				new Object[]{new AssemblyLineLogic(), new TestView()},
				new Object[]{new SteamGameModel(), new SteamGamePanel()},// works weird
				new Object[]{new AssemblyLineLogic(), new TestView()},
				new Object[]{new GameWeldingLogic(), new GameWeldingView()}, //press home button for controls
				new Object[]{new AssemblyLineLogic(), new TestView()},
				new Object[]{new EndGameLogic(), new EndGameView()}};
		fullscreen = true;
		gameinit();
		i=0;
		setGame((MiniGameLogic)list[i][0], (MiniGameView)list[i][1]);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viewSlot);;
		frame.setVisible(true);
		callResize();
		gameloop();
		
	}

	private void gameinit() {
		System.out.println("Connecting wiimotes...");
		while (wiimotes == null)
		{
			wiimotes = WiiUseApiManager.getWiimotes(1, true);
			if (wiimotes.length < 1)
				wiimotes = null;
		}
		
	}

	private void gameloop() throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException
	{
//		if (i != 1)
//			GameSoundtrack.GameMusic();
		GameSoundtrack.GameMusic();
		Thread draw = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
				try {
				Thread.sleep(10);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				if (Main.fullscreen) {
					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					gd.setFullScreenWindow(frame);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				}
				
				frame.repaint();
				}
				


			}
		});
		draw.start();
		while (true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			logicSlot.tick();

			if (logicSlot.isDone())
			{
				i++;
				if (i >= list.length) {
					list = new Object[][]{
							new Object[]{new OpeningModel(), new OpeningView()},
							new Object[]{new StartScreenStoryLogic(), new StartScreenStoryView()},//starts to early, suggestion. wait for first tick
							new Object[]{new AssemblyLineLogic(), new TestView()},
							new Object[]{new GamePowerLogic(), new TestView()},
							new Object[]{new AssemblyLineLogic(), new TestView()},
							new Object[]{new WaterSupplyGame(), new WaterSupplyView()}, //not working
							new Object[]{new AssemblyLineLogic(), new TestView()},
							new Object[]{new SteamGameModel(), new SteamGamePanel()},// works weird
							new Object[]{new AssemblyLineLogic(), new TestView()},
							new Object[]{new GameWeldingLogic(), new GameWeldingView()}, //press home button for controls
							new Object[]{new AssemblyLineLogic(), new TestView()},
							new Object[]{new EndGameLogic(), new EndGameView()}};
					i = 0;
				}
//				frame.removeAll();
				frame.getContentPane().removeAll();
				setGame((MiniGameLogic)list[i][0], (MiniGameView)list[i][1]);
				frame.getContentPane().add(viewSlot);
				frame.getContentPane().validate();
				frame.repaint();
			}
		}
	}

	public static boolean isFullscreen() {
		return fullscreen;
	}

	public static void setFullscreen(boolean fullscreen) {
		Main.fullscreen = fullscreen;
	}

	public MiniGameLogic getLogicSlot()
	{
		return logicSlot;
	}

	public void setLogicSlot(MiniGameLogic logicSlot)
	{
		this.logicSlot = logicSlot;
	}

	public JPanel getViewSlot()
	{
		return viewSlot;
	}

	public void setViewSlot(MiniGameView viewSlot)
	{
		this.viewSlot = viewSlot;
	}

	public static ArrayList<GameObject> getObjects() {
		return logicSlot.getObjects();
	}

	public static void callResize() {
		
		viewSlot.resize();
		frame.setSize(resX, resY);
	}
	
	public void setGame(MiniGameLogic logic, MiniGameView view) {
		wiimotes[0].removeWiiMoteEventListeners(logicSlot);
		if (view != null)
			viewSlot = view;
		if (logic != null)
			logicSlot = logic;
		wiimotes[0].removeWiiMoteEventListeners(logicSlot);
		logicSlot.giveMotes(wiimotes);
	}	
}
