package openingscreen;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main2 extends JFrame
{

	public static void main(String[]args)
	{
		Main2 main = new Main2();
	}
	
	public Main2()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1920,1080));
		
		OpeningView view = new OpeningView();
		
		add(view);
		pack();
		setVisible(true);
	}
}
