package stijgmachine.jti1a1.nl.model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GamePipes;
import stijgmachine.jti1a1.nl.objects.GrappleCursor;
import stijgmachine.jti1a1.nl.objects.MusicPlayer;
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

	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>(); 
	private ArrayList<GamePipes> floatingPipes = new ArrayList<GamePipes>();
	private GamePipes pipe; 
	private boolean done, drag, go = false; 
	private boolean add, mapStart = true; 
	private int index = 0; 
	private Point2D cursorLocation, pipeLocation, startLocation; 
	private Dimension size; 
	private int addTime = 0;
	private Wiimote wiimote;
	private MusicPlayer musicPlayer = new MusicPlayer();
	private GamePipes floatingPipe;

	public WaterSupplyGame() {
		gameObjects.add(new GameCursor());
		startLocation = new Point2D.Double (85,440);
		this.size = new Dimension(Main.resX / 8,Main.resY / 8);
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(175, 880), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(155+ size.getWidth(), 870), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(175+ (size.getWidth()*2), 880), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(1500, 880), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(1300, 860), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(1100, 870), 0, size));
		floatingPipes.add(floatingPipe = new GamePipes(new Point2D.Double(700, 890),0,size));

	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) {
		if(event.isButtonDownPressed()){
			gameObjects.clear();
			gameObjects.add(new GrappleCursor());
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
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent event) {

	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent event) {
		

	}

	public void onDisconnectionEvent(DisconnectionEvent event) {

	}

	@Override
	public void onExpansionEvent(ExpansionEvent event) {
		

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent event) {
		

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent event) {

	}

	@Override
	public void onIrEvent(IREvent event) {
		setCursorLocation(new Point2D.Double((int)(event.getAx() * 1.75),(int)(event.getAy() * 1.65)));
		setPipeLocation(new Point2D.Double(((int)getCursorLocation().getX()),(int)getCursorLocation().getY()));
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent event) {
		

	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent event) {
		

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent event) {
		

	}

	@Override
	public void onStatusEvent(StatusEvent event) {

	}

	@Override
	public void tick() {
		addTime ++;
		for (GamePipes o: floatingPipes){
			o.move();
		}
		
		if (addTime >= 100){
		if (add){
			Iterator it = floatingPipes.iterator();
			while(it.hasNext()){
				GamePipes o = (GamePipes) it.next();
			if (getCursorLocation().getX() > o.getLocation().getX()
						&& getCursorLocation().getX() < o.getLocation().getX() + size.getWidth()
						&& getCursorLocation().getY() > o.getLocation().getY() 
						&& getCursorLocation().getY() < o.getLocation().getY() + size.getWidth()){
					addPipe((int)Math.random()*5, index);
					addTime = 0;
					add = false;
					it.remove();	
				}
			}	
		}
		}
		if (go){
			((GameCursor)gameObjects.get(0)).update((int)getCursorLocation().getX(),(int)getCursorLocation().getY());
			map();
		}
	}
	
	public void removeSelectedPipe(){
		for (GameObject gameObject : gameObjects){
			if (gameObject.getClass() == GamePipes.class){
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
			if (gameObject.getClass()== GamePipes.class){
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
		return done;
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		list.addAll(gameObjects);
		list.addAll(floatingPipes);
		return list;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();
		wiimotes[0].setVirtualResolution(1920, 1080);
		wiimote = wiimotes[0];
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
			if (gameObject.getClass() == GamePipes.class){
				pipeDrag();
				// mapStart works
				if(mapStart){
					if ( ((GamePipes) gameObject).getLocation().getY() < startLocation.getY() &&	 ((GamePipes) gameObject).getLocation().getY() < startLocation.getY() + size.getHeight() 
						&&((GamePipes)gameObject).getLocation().getX() > startLocation.getX() && ((GamePipes) gameObject).getLocation().getX() < startLocation.getX() + size.getWidth() ){
						((GamePipes) gameObject).setLocation(new Point2D.Double(105,440));
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
							((GamePipes)gameObject).setLocation(new Point2D.Double (pipe.getBackLocation().getX(), pipe.getBackLocation().getY()-size.getHeight()/2));
							((GamePipes) gameObject).setMoveable(false);
							pipe = ((GamePipes)gameObject);
							if (pipe.getBackLocation().getX() > 1700)
								gameOver();
							add = true;
						}
					}
				}
			}
		}
	}
	
	public void gameOver() {
		musicPlayer.playSound("./sound/waterFinished.wav", 0);
		rumbleMote();
		for (int time = 0; time < 10000; time++);{
			System.out.println("Stall some time to play the sound");
		}
		done = true;
	}

	private void addPipe(int number, int indexNr) {
			for (GamePipes o: floatingPipes){
				if (getCursorLocation().getX() > o.getLocation().getX()
					&& getCursorLocation().getX() < o.getLocation().getX() + size.getWidth()
					&& getCursorLocation().getY() > o.getLocation().getY() 
					&& getCursorLocation().getY() < o.getLocation().getY() + size.getWidth()){
					addTime = 0;
					
					rumbleMote();
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
	}
	
	private void rumbleMote(){
		for (int t = 0; t < 20; t++){
			wiimote.activateRumble();
		}
		wiimote.deactivateRumble();
	}
}