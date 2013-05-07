package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;
import java.util.LinkedList;

import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GamePipes;
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

public class GameWaterVoorziening extends MiniGameLogic {

	private ArrayList<GameObject> GameObjects = new ArrayList<GameObject>();
	private LinkedList<Boolean> routes = new LinkedList<Boolean>();
	private boolean done,drag = false;
	private int index = 1;

	public GameWaterVoorziening() {
		GameObjects.add(new GameCursor());
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) {
		// TODO Auto-generated method stub
		if(event.isButtonDownPressed()){
			GameObjects.clear();
			GameObjects.add(new GameCursor());
		}
		if(event.isButtonHomePressed())
			System.exit(0);
		if(event.isButtonAPressed())
			GameObjects.add(new GamePipes(10, 10, index));
		if(event.isButtonBPressed())
			drag = true;
		drag = false;	
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExpansionEvent(ExpansionEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onIrEvent(IREvent event) {
		((GameCursor) GameObjects.get(0)).update(event.getAx(), event.getAy());
		if (drag)
			((GamePipes) GameObjects.get(index)).update(event.getAx(), event.getAy());
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusEvent(StatusEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return done;
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		// TODO Auto-generated method stub
		return GameObjects;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		// TODO Auto-generated method stub
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();
	}

	public void gameOver() {
		done = true;
	}

	public double getCursorX() {
		return 0;
	}

	public double getCursorY() {
		return 0;
	}

	private void addPipe(int number) {
		switch (number) {
		case 0: /* straight horizontal pipe */
			GameObjects.add(new stijgmachine.jti1a1.nl.objects.GamePipes(10,10,number));
			break;
		case 1: /* straight vertical pipe */
			break;
		case 2: /* bend up/right pipe */
			break;
		case 3: /* bend right/down pipe */
			break;
		case 4: /* bend down/left pipe */
			break;
		case 5: /* bend left/up pipe */
			break;
		}
	}

}
