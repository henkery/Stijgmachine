package stijgmachine.jti1a1.nl.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameObject;

public abstract class MiniGameView extends JPanel {
	
	public static final int STARTMENU = 0;
	public static final int TEST = 1;
	public static final int GAMEWELDING = 4;
	
	public MiniGameView()
	{
		this.setPreferredSize(new Dimension(1366, 768));
	}
	
	public abstract int getID();
	
	public void drawObjects(ArrayList<GameObject> objects, Graphics2D g) {
		for (GameObject item : objects)
		{
			item.predraw(g, this.getSize().height, this.getSize().width, 0, 0);
		}
	}
	
	public void resize()
	{
		this.setPreferredSize(new Dimension(Main.resX, Main.resY));
		this.setSize(Main.resX, Main.resY);
	}
	
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.clearRect(0, 0, Main.resX, Main.resY);
		drawObjects(Main.getObjects(), g2);
	}
	

}
