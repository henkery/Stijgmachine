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
	
	public MiniGameView()
	{
		this.setPreferredSize(new Dimension(800, 600));
	}
	
	public abstract int getID();
	
	public void drawObjects(ArrayList<GameObject> objects, Graphics2D g) {
		for (GameObject item : objects)
		{
			item.draw(g);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 800, 600);
		drawObjects(Main.getObjects(), g2);
	}

}
