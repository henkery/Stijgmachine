package stijgmachine.jti1a1.nl.controller;

import java.util.ArrayList;

import javax.swing.*;

import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.view.*;

public class Main {

	/**
	 * @param args
	 */
	
	private static MiniGameLogic logicSlot;
	private MiniGameView viewSlot;
	private JFrame frame;
	
	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main()
	{
		frame = new JFrame();
		setLogicSlot(new StartMenuLogic());
		viewSlot = new StartMenuView();
		//frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(viewSlot);
		frame.setVisible(true);
		frame.pack();
		gameloop();
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
				switch (viewSlot.getID()){
					case MiniGameView.STARTMENU:
						viewSlot = new TestView();
						break;
					
					case MiniGameView.TEST:
						viewSlot = new StartMenuView();
						break;
					
				}
				frame.revalidate();
				frame.getContentPane().add(viewSlot);
				setLogicSlot(new StartMenuLogic());
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
		// TODO Auto-generated method stub
		return logicSlot.getObjects();
	}
	
}
