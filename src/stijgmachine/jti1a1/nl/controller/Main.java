package stijgmachine.jti1a1.nl.controller;

import javax.swing.*;

import stijgmachine.jti1a1.nl.model.*;
import stijgmachine.jti1a1.nl.view.*;

public class Main {

	/**
	 * @param args
	 */
	
	private MiniGameLogic logicSlot;
	private JPanel viewSlot;
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
		frame.setSize(800, 600);
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			viewSlot = new TestView();
			frame.revalidate();
			logicSlot.tick();
			frame.getContentPane().add(viewSlot);
			if (logicSlot.isDone())
				System.exit(0);
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

	public void setViewSlot(JPanel viewSlot)
	{
		this.viewSlot = viewSlot;
	}
	
}
