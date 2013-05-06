package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public class TestView extends MiniGameView {

	public TestView(){
		super();
		this.add(new JLabel("nee"));
		System.out.println("nee");
	}

	public int getID() {
		// TODO Auto-generated method stub
		return MiniGameView.TEST;
	}
	
	/*public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, 800, 600);
		Shape lel = new Rectangle2D.Double(50,100,20,30);
		g2.fill(lel);
	}*/
}
