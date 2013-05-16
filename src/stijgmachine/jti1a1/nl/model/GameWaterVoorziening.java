package stijgmachine.jti1a1.nl.model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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

	/* grote grid = 8x8 */
	private ArrayList<GameObject> GameObjects = new ArrayList<GameObject>();
	private LinkedList<Boolean> routes = new LinkedList<Boolean>();
	private boolean done,drag,go = false;
	private boolean add = true;
	private int index = 0;
	private Point2D cursorLocation;
	private Point2D pipeLocation;
	private Dimension size;

	public GameWaterVoorziening() {
		GameObjects.add(new GameCursor());
		this.size = new Dimension(20,20);
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) {
		// TODO Auto-generated method stub
		if(event.isButtonDownPressed()){
			GameObjects.clear();
			GameObjects.add(new GameCursor());
			index = 0;
			nextPipe();
		}
		if(event.isButtonRightPressed())
			nextPipe();
		if(event.isButtonHomePressed())
			System.exit(0);
		if(event.isButtonAPressed()){
			if(add){
			int i = index;
			addPipe(0, i+1);
			add = false;
			go = true;
			}
		}
		if(event.isButtonBPressed()){
			if (!add)
				drag = true;
			}
	}
	
	public void nextPipe(){
		add = true;
		drag = false;
		index ++;
	}
	
	public void movePipe(){
		if (!drag)
			return;
		if (drag){
			setPipeLocation(new Point2D.Double (getCursorLocation().getX(),getCursorLocation().getY()));
		}
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
		setCursorLocation(new Point2D.Double((int)event.getAx(),(int)event.getAy()));
		setPipeLocation(new Point2D.Double((int)getCursorLocation().getX(),(int)getCursorLocation().getY()));
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
		if (go){
			pipeUpdate();
			((GamePipes) GameObjects.get(index)).update((int)getPipeLocation().getX(),(int)getPipeLocation().getY());
			}
		System.out.println(drag);
		drag = false;
		
	}
	
	public void pipeUpdate(){
		for (GameObject gameObject : GameObjects){
			if (gameObject.getID() == GamePipes.id){
				if(drag)
					((GamePipes) gameObject).update((int)getPipeLocation().getX(),(int)getPipeLocation().getY());
			}	
		}
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
	
	private void setCursorLocation(Point2D point){
		cursorLocation = point;
	}

	private Point2D getCursorLocation(){
		return cursorLocation;
	}
	
	private void setPipeLocation(Point2D point){
		pipeLocation = point;
	}
	
	private Point2D getPipeLocation(){
		return pipeLocation;
	}

	public void gameOver() {
		done = true;
	}

	private void addPipe(int number, int index) {
		int numbers = number;
		int i = index;
		switch (numbers) {
		case 0: /* straight horizontal pipe */
			GameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),i, size));
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
