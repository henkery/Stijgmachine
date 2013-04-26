package stijgmachine.jti1a1.nl.controller;

import javax.swing.*;

import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.view.*;

public class Main {

	/**
	 * @param args
	 */
	
	private MiniGameLogic logicSlot;
	private MiniGameView viewSlot;
	
	public static void main(String[] args)
	{
		Main game = new Main();
	}
	
	public Main()
	{
		JFrame frame = new JFrame();
		setLogicSlot(new StartMenuLogic());
		setViewSlot(new StartMenuView());
		frame.setContentPane(MiniGameView.panel);
		frame.setSize(800, 600);
		gameloop();
	}

	private void gameloop()
	{
		while (true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logicSlot.tick();
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

	public MiniGameView getViewSlot()
	{
		return viewSlot;
	}

	public void setViewSlot(MiniGameView viewSlot)
	{
		this.viewSlot = viewSlot;
	}
	
}
