package stijgmachine.jti1a1.nl.startScreenStory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GameSoundtrack;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class StartScreenStoryLogic extends MiniGameLogic implements ActionListener
{
	private boolean isDone = false;
	private boolean soundStarted  = false;
	private int counter = 0;
	private Timer t;

	public StartScreenStoryLogic() throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		StartScreenStoryView.setBackground(ImageIO.read(getClass().getResource("/res/intro_slatendo.png")));
//		GameSoundtrack.IntroTune();
		t = new Timer(3000,this);
//		t.start();	
	}

	public void setDone(boolean isDone)
	{
		t.stop();
		this.isDone = isDone;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		counter++;
		try
		{
			StartScreenStoryView.setBackground(ImageIO.read(getClass().getResource("/res/intro_story.png")));
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(counter);
		
		if(counter == 6) isDone = true;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick()
	{
		if(!soundStarted)
		{
			try {
				GameSoundtrack.IntroTune();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			soundStarted = true;
			t.start();
		}


	}

	@Override
	public boolean isDone()
	{
		// TODO Auto-generated method stub
		if (isDone)
			t.stop();
		return isDone;
	}

	@Override
	public ArrayList<GameObject> getObjects()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes)
	{
		// TODO Auto-generated method stub
		
	}

}
