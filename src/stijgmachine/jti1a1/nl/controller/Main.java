package stijgmachine.jti1a1.nl.controller;

import java.awt.Frame;
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
	public static int resX = 1920; 
	public static int resY = 1080; 
	
	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main()
	{
		gameinit();
		setGame(new WaterSupplyGame(), new WaterSupplyView());
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
		while (true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logicSlot.tick();
			
			if (logicSlot.isDone())
			{
				logicSlot = null;
				/*switch (viewSlot.getID()){
					case MiniGameView.STARTMENU:
						viewSlot = new TestView();
						//setLogicSlot(new TestLogic());
						logicSlot = new TestLogic();
						break;
					
					case MiniGameView.TEST:
						viewSlot = new StartMenuView();
						setLogicSlot(new StartMenuLogic());
						break;
					
				}*/
				setGame(new TestLogic(), null);
				//frame.getContentPane().removeAll();
				//frame.revalidate();
				//frame.getContentPane().add(viewSlot);
			//	
				logicSlot.giveMotes(wiimotes);
			}
			//viewSlot.repaint();
			frame.repaint();
			//System.out.println(((MiniGameView) frame.getContentPane().getComponents()[0]).getID());
			//frame.getContentPane().repaint();
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
		// TODO Auto-generated method stub
		
		return logicSlot.getObjects();
	}

	public static void callResize() {
		
		viewSlot.resize();
		frame.setSize(resX, resY);
	}
	
	public void setGame(MiniGameLogic logic, MiniGameView view) {
		if (view != null)
			viewSlot = view;
		//System.out.println("view is in");
		if (logic != null)
			logicSlot = logic;
		//System.out.println("logic is in");
		logicSlot.giveMotes(wiimotes);
	}
	
	
	
}
