package stijgmachine.jti1a1.nl.controller;

import gameSteamCreation.SteamGameModel;
import gameSteamCreation.SteamGamePanel;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import stijgmachine.jti1a1.nl.gameWelding.GameWeldingLogic;
import stijgmachine.jti1a1.nl.gameWelding.GameWeldingView;
import stijgmachine.jti1a1.nl.model.EndGameLogic;
import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.model.TestLogic;

import stijgmachine.jti1a1.nl.GamePower.GamePowerLogic;
import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.view.EndGameView;
import stijgmachine.jti1a1.nl.view.MiniGameView;
import stijgmachine.jti1a1.nl.view.TestView;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

public class Main {

	/**
	 * @param args
	 */
	
	private static MiniGameLogic logicSlot;
	private static MiniGameView viewSlot;
	private static SteamGameModel steamModel;
	private static JFrame frame;
	private Wiimote[] wiimotes;
	private static boolean fullscreen;
	public static int resX = 1366; 
	public static int resY = 768; 
	public Object[][] list;
	private int i;
	
	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main()
	{
		list = new Object[][]{
				new Object[]{new GamePowerLogic(), new TestView()},
				new Object[]{new GameWeldingLogic(), new GameWeldingView()},
				new Object[]{new EndGameLogic(), new EndGameView()}};
		fullscreen = false;
		gameinit();
		i=0;
		setGame((MiniGameLogic)list[i][0], (MiniGameView)list[i][1]);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viewSlot);
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

	private void gameloop()
	{
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
				setGame((MiniGameLogic)list[i][0], (MiniGameView)list[i][1]);
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
		if (view != null)
			viewSlot = view;
		if (logic != null)
			logicSlot = logic;
		logicSlot.giveMotes(wiimotes);
	}	
}
