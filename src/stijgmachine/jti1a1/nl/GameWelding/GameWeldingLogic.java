package stijgmachine.jti1a1.nl.gameWelding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GameObjectContainer;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class GameWeldingLogic extends MiniGameLogic implements ActionListener
{
	private ArrayList<GameObject> items;
	private ArrayList<Rectangle2D> pointsHit;
	private GameObjectContainer containerLeft;
	private GameWeldingDoor door;
	private GameWeldingWeld weld;
	private GameWeldingCursor cursorLeft;
	private GameWeldingCursor cursorRight;
	private GameWeldingSparks sparks;
	private GameWeldingSteam steam;
	private Clip clip;


	private int x;
	private int y;
	
	private boolean inWeldingArea = false;
	private boolean collisionDetected;
	private boolean controlsActivated =  false;
	private boolean isDone = false;
	private boolean isWelding = false;
	
	
	public GameWeldingLogic() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		items = new ArrayList<GameObject>();
		pointsHit = new ArrayList<Rectangle2D>();
		containerLeft = new GameWeldingLeftContainer(0, 0, 191, 1080, 204);
		door = new GameWeldingDoor();
		weld = new GameWeldingWeld();
		pointsHit.addAll(door.getPoints());
		steam = new GameWeldingSteam();
		sparks = new GameWeldingSparks(x, y);
		
		getAudio();
		getImages();

		items.add(containerLeft);
		items.add(steam);
		items.add(sparks);
		items.add(door);
		items.add(weld);
		items.add(cursorLeft);
		items.add(cursorRight);
		
		x = cursorRight.getX();
		y = cursorRight.getY();
				
		Timer timer = new Timer(1000/100, this);
		timer.start();
	}
	
	public void getAudio() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("welding.wav")).getFormat()));
		clip.open(AudioSystem.getAudioInputStream(new File("welding.wav")));			
	}
	
	public void getImages()
	{
		try
		{
			cursorLeft = new GameWeldingCursor("left", 4, 654, ImageIO.read(getClass().getResource("/res/welding_wire.png")));
			cursorRight = new GameWeldingCursor("right", -30, 160, ImageIO.read(getClass().getResource("/res/welding_torch.png")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}			
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{		
		weld = (GameWeldingWeld) items.get(items.size()-3);
		door = (GameWeldingDoor) items.get(items.size()-4);
		
		if(arg0.isButtonHomePressed()) controlsActivated = true;
		
		else if(controlsActivated && collisionDetected && inWeldingArea)
		{
			if(arg0.isButtonAPressed())
			{
				weld.startWeld(x, y);
	
				if(arg0.isButtonAHeld())
				{
					if(weld.isStarted())
					{
						weld.continueWeld(x, y);
						isWelding = true;
					}									
					for(Rectangle2D p : door.getPoints())
					{
						if(weld.getPath().intersects(p))
						{
							pointsHit.remove(p);		
						}					
					}
				}
			}
		}
		
		
		
		if (!arg0.isButtonAHeld())
		{
			weld.saveWeld();
			isWelding = false;
		}
		
		isDone = pointsHit.isEmpty();
		
		System.err.println("Points Hit: "+pointsHit.size());
		System.out.println("Is Done: "+isDone);
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0)
	{
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0)
	{
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0)
	{
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0)
	{
		cursorLeft = ((GameWeldingCursor) items.get(items.size() - 2));
		
		if(controlsActivated)
		{	
			NunchukEvent nunchuck = (NunchukEvent) arg0;
			JoystickEvent joystickLeft = nunchuck.getNunchukJoystickEvent();
			NunchukButtonsEvent buttons = nunchuck.getButtonsEvent();
			
			int m = (int) ((joystickLeft.getMagnitude() * 10) / 4);
			if (m > 9)
				m = 9;
	
			if (joystickLeft.getAngle() > 0 && joystickLeft.getAngle() < 50)
			{
				cursorLeft.update(cursorLeft.getX() + m, cursorLeft.getY() - m);
			}
	
			if (joystickLeft.getAngle() > 45 && joystickLeft.getAngle() < 95)
			{
				cursorLeft.update(cursorLeft.getX() + m, cursorLeft.getY() - m);
			}
	
			if (joystickLeft.getAngle() > 90 && joystickLeft.getAngle() < 140)
			{
				cursorLeft.update(cursorLeft.getX() + 1 * m, cursorLeft.getY() + 1
						* m);
			}
	
			if (joystickLeft.getAngle() > 135 && joystickLeft.getAngle() < 185)
			{
				cursorLeft.update(cursorLeft.getX() + 1 * m, cursorLeft.getY() + 2
						* m);
			}
	
			if (joystickLeft.getAngle() > 180 && joystickLeft.getAngle() < 230)
			{
				cursorLeft.update(cursorLeft.getX() - 1 * m, cursorLeft.getY() + 2
						* m);
			}
	
			if (joystickLeft.getAngle() > 225 && joystickLeft.getAngle() < 275)
			{
				cursorLeft.update(cursorLeft.getX() - 2 * m, cursorLeft.getY() + 1
						* m);
			}
	
			if (joystickLeft.getAngle() > 270 && joystickLeft.getAngle() < 320)
			{
				cursorLeft.update(cursorLeft.getX() - 2 * m, cursorLeft.getY() - 1
						* m);
			}
	
			if (joystickLeft.getAngle() > 315 && joystickLeft.getAngle() < 365)
			{
				cursorLeft.update(cursorLeft.getX() - 1 * m, cursorLeft.getY() - 2
						* m);
			}
	
		}
//		System.err.println("Links x: "+cursorLeft.getX()+" y: "+cursorLeft.getY());

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		cursorRight = ((GameWeldingCursor) items.get(items.size() - 1));
		sparks = ((GameWeldingSparks) items.get(items.size() - 5));
		collisionDetected = cursorLeft.getDetector().getRectangle().intersects(cursorRight.getDetector().getRectangle());
		inWeldingArea = door.getWeldingArea().intersects(cursorRight.getDetector().getRectangle());

		
		if(controlsActivated)
		{	
			x = (int)(arg0.getAx()*1.65);
			y = (int)(arg0.getAy()*1.65);
			
			cursorRight.update(x, y);
			sparks.update(x, y);

		}
		
//		System.out.println("Rechts x: "+arg0.getAx()+" y: "+arg0.getAy());
		System.out.println(inWeldingArea);
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		System.out.println("Nunchuk Inserted");
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		System.out.println("Nunchuk Removed");
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		System.out.println(arg0.getBatteryLevel());
	}

	@Override
	public void tick()
	{
	}

	@Override
	public boolean isDone()
	{
		return isDone;
	}

	@Override
	public ArrayList<GameObject> getObjects()
	{
		return items;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes)
	{
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateContinuous();
		wiimotes[0].activateIRTRacking();		
		wiimotes[0].activateSmoothing();
		wiimotes[0].setSensorBarBelowScreen();
		wiimotes[0].setScreenAspectRatio169();
		wiimotes[0].setVirtualResolution(1920, 1080);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{		
		if(isWelding)
		{
			clip.start();			
		}
		else clip.stop();
		
		for (Iterator<Particle> itr = steam.getList().iterator(); itr.hasNext();)
		{
			Particle p = itr.next();

			if (p.getY() < -100.0)
			{
				itr.remove();
			} else
			{
				p.update();
			}
		}

		if (steam.getList().size() < 1000)
		{
			steam.getList().add(new Particle("steam"));
		}			

	}
}
