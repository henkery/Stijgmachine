package stijgmachine.jti1a1.nl.model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GamePipes;
import stijgmachine.jti1a1.nl.view.WaterSupplyView;
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

public class WaterSupplyGame extends MiniGameLogic {

	/* grote grid = 8x8 */
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(); // haat aan dit attribuut
	private GamePipes pipe; // haat aan dit attribuut
	private boolean done, drag, go = false; // haat aan dit attribuut
	private boolean add, mapStart = true; // haat aan dit attribuut
	private int index = 0; // haat aan dit attribuut
	private Point2D cursorLocation, pipeLocation, startLocation, attachLocation; // haat aan dit attribuut
	private Dimension size; // haat aan dit attribuut
	private WaterSupplyView view;
	private int addTime = 0;

	public WaterSupplyGame() {
//		gameObjects.add(view);
		gameObjects.add(new GameCursor());
		startLocation = new Point2D.Double (0,200);
		this.size = new Dimension(Main.resX / 8,Main.resY / 8);
//		view = new WaterSupplyView();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) {
		// TODO Auto-generated method stub
		if(event.isButtonDownPressed()){
			gameObjects.clear();
			gameObjects.add(new GameCursor());
			index = 0;
			add = true;
			drag = false;
			mapStart = true;
		}
		if(event.isButtonRightJustReleased())
			add = true;
		if(event.isButtonHomePressed())
			System.exit(0);
		if(event.isButtonPlusJustPressed()){
			go = true;
			add = true;
		}
		if(event.isButtonAPressed()){
			if (!add)
				drag = true;	
		}
		if(!event.isButtonAPressed())
			drag = false;
		if(event.isButtonBJustReleased())
//			map();
		if(event.isButtonAHeld()){
			if(!add)
				drag = true;
		}
		if(event.isButtonUpPressed()){
			if (!add){
				if (gameObjects.size()-1 > 1)
					gameObjects.remove(gameObjects.size()-1);
			}
			add = true;
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
		setPipeLocation(new Point2D.Double(((int)getCursorLocation().getX()),(int)getCursorLocation().getY()));
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
		if (add)
			addTime++;
		if(addTime > 500){
			if (add){
				addPipe((int)Math.random()*5, index);
				add = false;
				addTime = 0;
			}
		}
		System.out.println("addTime: "+ addTime);
		if (go){
			((GameCursor)gameObjects.get(0)).update((int)getCursorLocation().getX(),(int)getCursorLocation().getY());
//			pipeDrag();
			map();
		}
		for (GameObject o : gameObjects)
		{
			System.out.println(o.getX() + "," + o.getY());
		}
		
	}
	
	public void removeSelectedPipe(){
		for (GameObject gameObject : gameObjects){
			if (gameObject.getID() == GamePipes.id){
				if (getCursorLocation().getX() > ((GamePipes) gameObject).getLocation().getX() - 50
						&& getCursorLocation().getX() < ((GamePipes) gameObject).getLocation().getX() + 50
						&& getCursorLocation().getY() > ((GamePipes) gameObject).getLocation().getY() - 50
						&& getCursorLocation().getY() < ((GamePipes) gameObject).getLocation().getY() + 50){
					int ind = ((GamePipes)gameObject).getIdPipe();
					gameObjects.remove(ind);
				}
			}
		}
	}
	
	public void removeCurrentPipe(){
		if (gameObjects.size()-1 > 1)
			gameObjects.remove(gameObjects.size()-1);
	}
	
	public void pipeDrag(){
		if(!drag)
			return;
		for (GameObject gameObject : gameObjects){
			if (gameObject.getID() == GamePipes.id){
					if (getCursorLocation().getX() > ((GamePipes) gameObject).getLocation().getX() - size.getWidth()/2
						&& getCursorLocation().getX() < ((GamePipes) gameObject).getLocation().getX() + size.getWidth()/2
						&& getCursorLocation().getY() > ((GamePipes) gameObject).getLocation().getY() - size.getHeight()/2
						&& getCursorLocation().getY() < ((GamePipes) gameObject).getLocation().getY() + size.getHeight()/2) {
							((GamePipes) gameObject).update((int)getPipeLocation().getX(),(int)getPipeLocation().getY());
				}
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
		return gameObjects;
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

	private void map(){
		for(GameObject gameObject : gameObjects){
			if (gameObject.getID() == GamePipes.id){
				pipeDrag();
				// mapStart works
				if(mapStart){
					if ( ((GamePipes) gameObject).getLocation().getY() < startLocation.getY() &&	 ((GamePipes) gameObject).getLocation().getY() < startLocation.getY() + size.getHeight() 
						&&((GamePipes)gameObject).getLocation().getX() > startLocation.getX() && ((GamePipes) gameObject).getLocation().getX() < startLocation.getX() + size.getWidth() ){
						((GamePipes) gameObject).setLocation(new Point2D.Double(80,100));
						((GamePipes) gameObject).setMoveable(false);
						pipe = new GamePipes(((GamePipes)gameObject).getLocation(), 0,size);
						add = true;
						mapStart = false;
					}
				}
				//test for the second one;
				if (!mapStart){
					if (((GamePipes)gameObject).getLocation().getX() > pipe.getLocation().getX()
							&& ((GamePipes)gameObject).getLocation().getX() < pipe.getLocation().getX() + size.getWidth()
							&& ((GamePipes)gameObject).getLocation().getY() > pipe.getLocation().getY()
							&& ((GamePipes)gameObject).getLocation().getY() < pipe.getLocation().getY() + size.getHeight()) {
						if(((GamePipes)gameObject).getConnection(((GamePipes)gameObject).getFrontDirection(),((GamePipes)gameObject).getBackDirection() , pipe.getFrontDirection(), pipe.getBackDirection())){
							
							//Set a proper location for the gamePipes
							((GamePipes)gameObject).setLocation(new Point2D.Double (pipe.getBackLocation().getX(), pipe.getBackLocation().getY()-size.getHeight()/2     ));
							((GamePipes) gameObject).setMoveable(false);
							pipe = ((GamePipes)gameObject);
							add = true;
							for (int i = 0; i<10; i++){
								System.out.println("The eagle has left the building!");
							}
						}
					}
				}
			}
		}
	}
	
	public void gameOver() {
		done = true;
	}

	private void addPipe(int number, int indexNr) {
		switch (number) {
			case 0: /* straight horizontal pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			case 1: /* straight vertical pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			case 2: /* bend up/right pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			case 3: /* bend right/down pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			case 4: /* bend down/left pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			case 5: /* bend left/up pipe */
				gameObjects.add(new GamePipes((int)getPipeLocation().getX(),(int)getPipeLocation().getY(),number,indexNr, size));
				break;
			}
		index++;
	}
}