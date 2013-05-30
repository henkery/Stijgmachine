package stijgmachine.jti1a1.nl.GameWelding;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameButton;
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

public class GameWeldingLogic extends MiniGameLogic
{
	private ArrayList<GameObject> items;
	private GameObjectContainer containerLeft;
	private GameWeldingDoor door;
	private GameWeldingWeld weld;
	private GameWeldingCursor cursorLeft;
	private GameWeldingCursor cursorRight;

	private int x;
	private int x2;
	private int y;
	private int y2;

	private boolean collisionDetected;
	private boolean controlsActivated =  false;
	private boolean isDone = false;
	private boolean zPressed;

	public GameWeldingLogic()
	{
		items = new ArrayList<GameObject>();
		containerLeft = new GameWeldingLeftContainer(0, 0, 191, 1080, 201);
		door = new GameWeldingDoor();
		weld = new GameWeldingWeld();
		
		try
		{
			cursorLeft = new GameWeldingCursor("left", 22, 588, ImageIO.read(getClass().getResource("/res/welding_wire.png")));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			cursorRight = new GameWeldingCursor("right", -21, 234, ImageIO.read(getClass().getResource("/res/welding_torch.png")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		x = cursorRight.getX();
		y = cursorRight.getY();

		items.add(containerLeft);
		items.add(door);
		items.add(weld);
		items.add(cursorLeft);
		items.add(cursorRight);
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
		weld = ((GameWeldingWeld) items.get(items.size() - 3));
		
		if(arg0.isButtonHomePressed()) controlsActivated = true;		

		if(controlsActivated && collisionDetected)
		{
			if(arg0.isButtonAPressed())
			{
				weld.startWeld(x, y);
			}
			if(arg0.isButtonAHeld())
			{
				if (weld.isStarted())
				{
				weld.startWeld(x, y);
				}
			}
			if (arg0.isButtonAJustReleased())
			{
				weld.stopWeld();
			}
		}
		
		else if(!collisionDetected) weld.stopWeld();		
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
	
			if(buttons.isButtonZPressed())
			{
				zPressed = true;
			}
			else zPressed = false;
			
			System.err.println(cursorLeft.getX());
			System.err.println(cursorLeft.getY());
		}

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
		
		if(controlsActivated)
		{	
			if(arg0.getAx() < (x+4) || arg0.getAx() < (x-4) || arg0.getAy() < (y+4) || arg0.getAy() < (y-4))
			{
				if(x < arg0.getAx()) x2 = (arg0.getAx()-2);
				if(x > arg0.getAx()) x2 = (arg0.getAx()+2);
				if(y < arg0.getAy()) y2 = (arg0.getAy()-2);
				if(y > arg0.getAy()) y2 = (arg0.getAy()+2);	
			}
			else
			{
				x2 = x; 
				y2 = y;
			}
			
			cursorRight.update(x2, y2);	
			
			x = x2;
			y = y2;
		}
		
//		System.out.println(arg0.getXVRes());
//		System.out.println(arg0.getYVRes());
				
		collisionDetected = cursorLeft.getDetector().getRectangle().intersects(cursorRight.getDetector().getRectangle());
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
		//return isDone;
		return false;
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
		//wiimotes[0].activateMotionSensing();
		wiimotes[0].setSensorBarBelowScreen();
		wiimotes[0].setScreenAspectRatio169();
		wiimotes[0].setVirtualResolution(1920, 1080);
	}
}
