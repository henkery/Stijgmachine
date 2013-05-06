package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;

import stijgmachine.jti1a1.nl.controller.SwipeEvent;
import stijgmachine.jti1a1.nl.objects.GameButton;
import stijgmachine.jti1a1.nl.objects.GameMenu;
import stijgmachine.jti1a1.nl.objects.GameMenuItem;
import stijgmachine.jti1a1.nl.objects.GameMenuResolutionSelector;
import stijgmachine.jti1a1.nl.objects.GameMenuSelector;
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

public class StartMenuLogic extends MiniGameLogic {
	
	private ArrayList<GameObject> items;

	private boolean done;

	private Wiimote wiimote;
	
	public StartMenuLogic() {
		menu1();
		done = false;
	}
	
	public void menu1(){
		items = new ArrayList<GameObject>();
		items.add(new GameMenu(new GameObject[]{
				new GameButton(1000, 200, GameObject.RELATIVE_FROM_BOTTOMRIGHT, 1000, 100, 42, "Start game"),
				new GameButton(20, 200, GameObject.ABSOLUTE, 1000, 100, 42, "settings"),	
				new GameButton(20, 380, GameObject.ABSOLUTE, 1000, 100, 42, "about"),
				new GameMenuResolutionSelector(20, 560, GameObject.ABSOLUTE)
				}, 0, 0, GameObject.ABSOLUTE));
	}
	
	public void menu2() {
		items = new ArrayList<GameObject>();
		items.add(new GameMenu(new GameObject[]{
				new GameButton(20, 20, GameObject.ABSOLUTE, 1000, 100, 42, "Secundaire menu"),
				new GameButton(20, 200, GameObject.ABSOLUTE, 1000, 100, 42, "Of niet"),	
				new GameButton(20, 380, GameObject.ABSOLUTE, 1000, 100, 42, "Druk op B"),
				new GameMenuResolutionSelector(20, 560, GameObject.ABSOLUTE)
				}, 0, 0, GameObject.ABSOLUTE));
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void tick() {
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.addAll(items);
		return objects;
	}
	

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimote = wiimotes[0];
		wiimote.addWiiMoteEventListeners(this);
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
		// TODO Auto-generated method stub
		
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

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		//System.out.println(arg0.getButtonsJustPressed());
		if (arg0.isButtonUpPressed())
		{
			((GameMenu) items.get(0)).itemUp();
		}
		else if (arg0.isButtonDownPressed())
		{
			((GameMenu) items.get(0)).itemDown();
		}
		else if (arg0.isButtonRightPressed())
		{
			((GameMenu) items.get(0)).itemRight();
		}
		else if (arg0.isButtonLeftPressed())
		{
			((GameMenu) items.get(0)).itemLeft();
		}
		else if (arg0.isButtonHomePressed())
		{
			menu2();
		}
		else if (arg0.isButtonAPressed())
		{
			switch (((GameMenu) items.get(0)).getPointer()){
				case (0):
					done = true;
				wiimote.removeWiiMoteEventListeners(this);
					break;
				default:
					items.get(0).click();
			}
		}
		else if (arg0.isButtonBPressed())
		{
			menu1();
		}
	}

}
