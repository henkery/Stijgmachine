package stijgmachine.jti1a1.nl.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import stijgmachine.jti1a1.nl.gameWelding.GameWeldingLogic;
import stijgmachine.jti1a1.nl.gameWelding.GameWeldingView;
import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GameSoundtrack;
import stijgmachine.jti1a1.nl.view.MiniGameView;
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
	public static int resX = 1920; 
	public static int resY = 1080; 
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		new Main();
	}
	
	public Main() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		gameinit();
		setGame(new GameWeldingLogic(), new GameWeldingView());
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viewSlot);
		frame.setVisible(true);
		callResize();
		gameloop();
		
	}

	private void gameinit() {
		System.out.println("Connecting wiimotes...");
//		while (wiimotes == null)
//		{
//			wiimotes = WiiUseApiManager.getWiimotes(1, true);
//			if (wiimotes.length < 1)
//				wiimotes = null;
//		}
		
	}

	private void gameloop() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
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
//				logicSlot = null;
//				setGame(new TestLogic(), null);
//				logicSlot.giveMotes(wiimotes);
				System.exit(0);
			}
		}
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
//		logicSlot.giveMotes(wiimotes);
	}
	
	
	
}
