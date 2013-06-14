package openingscreen;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameObject;
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

public class OpeningModel extends MiniGameLogic
{

	private static Image beginScreen, buttonA;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private StartText text;
	private static boolean clicked = false;
	
	public OpeningModel()
	{
		beginScreen = Toolkit.getDefaultToolkit().createImage("images/Fabriek.png");
		buttonA = Toolkit.getDefaultToolkit().createImage("images/buttonANieuw.png");
		
		text = new StartText(translatepixelX(500), translatepixelY(750), GameObject.ABSOLUTE);
		
		objects.add(text);
	}


	public static int translatepixelX(int xWaarde)
	{
		return (int) ((Main.resX/1600)*xWaarde);
	}
	
	public static int translatepixelY(int yWaarde)
	{
		return (int)((Main.resY/900)*yWaarde);
	}
	
	public static Image getBeginScreen()
	{
		return beginScreen;
	}
	
	public static Image getButtonA()
	{
		return buttonA;
	}
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if(e.isButtonAPressed() && clicked == false)
		{
			clicked = true;
		}
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
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
	}

	@Override
	public void tick()
	{
	}

	@Override
	public boolean isDone()
	{
		return clicked;
	}

	@Override
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes)
	{
		wiimotes[0].addWiiMoteEventListeners(this);
	}
	
}
