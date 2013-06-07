package stijgmachine.jti1a1.nl.controller;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.*;


import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.view.*;
import wiiusej.*;

public class Main {

	/**
	 * @param args
	 */
	
	private static MiniGameLogic logicSlot;
	private static MiniGameView viewSlot;
	private static JFrame frame;
	private Wiimote[] wiimotes;
	private static boolean fullscreen;
	public static int resX = 1366; 
	public static int resY = 768; 
	
	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main()
	{
		fullscreen = false;
		gameinit();
		setGame(new EndGameLogic(), new EndGameView());
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
				logicSlot = null;
				setGame(new TestLogic(), null);
				logicSlot.giveMotes(wiimotes);
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
