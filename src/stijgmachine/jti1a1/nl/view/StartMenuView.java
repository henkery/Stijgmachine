package stijgmachine.jti1a1.nl.view;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenuView extends MiniGameView {
	
	public StartMenuView()
	{
		super();
		this.add(new JLabel("hem"));
	}

	public int getID() {
		// TODO Auto-generated method stub
		return MiniGameView.STARTMENU;
	}

}
