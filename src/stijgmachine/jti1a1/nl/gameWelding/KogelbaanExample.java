package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class KogelbaanExample
{

	public static void main(String s[])
	{
		JFrame frame = new JFrame("KogelbaanExample voorbeeld");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new KogelbaanPanel();

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}

class KogelbaanPanel extends JPanel implements ActionListener
{

	private ArrayList<Kogel> kogels = new ArrayList<Kogel>();

	/* Constructor */
	public KogelbaanPanel()
	{
		setPreferredSize(new Dimension(640, 480));
		for (int idx = 0; idx < 2; idx++)
		{
			kogels.add(new Kogel());
		}

		Timer timer = new Timer(1000 / 30, this);
		timer.start();
	}

	// Timer, action performed. Update hier objects en/of world
	// en dan issue een repaint()
	public void actionPerformed(ActionEvent arg0)
	{
		for (Iterator<Kogel> itr = kogels.iterator(); itr.hasNext();)
		{
			Kogel k = itr.next();

			if (k.y < -200.0)
			{
				itr.remove();
			} else
			{
				k.update();
			}
		}

		if (kogels.size() < 1000)
		{
			kogels.add(new Kogel());
		}

		repaint();
	}

	// Hier alleen tekenen ! Nooit een repaint() !!
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// 0-punt verlegging
		g2.translate(0, getHeight());
		g2.scale(1, -1);

		// Center screen
		g2.translate(getWidth() / 2, getHeight() / 2);
		g2.scale(1, 1);

		for (Kogel k : kogels)
		{
			k.draw(g);
		}
	}
}
