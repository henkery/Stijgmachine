import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Main extends JFrame implements ActionListener
{

	Steam steam;
	
	public Main()
	{
		Timer timer = new Timer(1000/100,this);
		timer.start();
	}
	
	public static void main(String[]args)
	{
		Particle particle = new Particle();
		Steam steam = new Steam();
		Main main = new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for(Iterator<Particle> itr = steam.getList().iterator(); itr.hasNext();)
		{
			Particle p = itr.next();
			
			if(p.getY() < -100)
				itr.remove();
			else
				p.update();
		}
		
		if(steam.getList().size() < 1000)
			steam.getList().add(new Particle());
	}
}
