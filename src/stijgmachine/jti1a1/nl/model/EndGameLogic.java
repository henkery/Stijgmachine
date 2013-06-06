package stijgmachine.jti1a1.nl.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.objects.GameButton;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameImage;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.endgame.ObjectHolder;
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

public class EndGameLogic extends MiniGameLogic {
	
	private ArrayList<GameObject> objects;
	private GameCursor cursor;
	
	public EndGameLogic () {
		objects = new ArrayList<GameObject>();
		try {
			objects.add(new GameImage(-244, -348, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/Rocket-without-door.png"))));
			objects.add(new ObjectHolder(0, 0, 30, 30, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/door-holder.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		objects.add(new ObjectHolder(-100, -200, 30, 30, GameObject.RELATIVE_FROM_CENTER, null));
		objects.add(new ObjectHolder(-100, 300, 30, 30, GameObject.RELATIVE_FROM_CENTER, null));
		cursor = new GameCursor();
		objects.add(cursor);
	}
	

	@Override
	public ArrayList<GameObject> getObjects() {
		// TODO Auto-generated method stub
		return objects;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onIrEvent(IREvent arg0) {
		cursor.update(arg0.getAx(), arg0.getAy());
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusEvent(StatusEvent arg0) {
		// TODO Auto-generated method stub

	}

}
