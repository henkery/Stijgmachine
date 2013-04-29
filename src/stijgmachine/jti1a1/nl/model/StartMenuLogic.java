package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;

import stijgmachine.jti1a1.nl.controller.SwipeEvent;
import stijgmachine.jti1a1.nl.objects.GameButton;
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
	
	private ArrayList<GameButton> buttons;

	private int count;

	private boolean done;
	
	private int point;
	
	public StartMenuLogic() {
		count = 0;
		point = 0;
		buttons = new ArrayList<GameButton>();
		buttons.add(new GameButton(20, 20, 1000, 100, "henk"));
		buttons.add(new GameButton(20, 200, 1000, 100, "henk"));
		buttons.add(new GameButton(20, 380, 1000, 100, "henk"));
		buttons.add(new GameButton(20, 560, 1000, 100, "henk"));
		done = false;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	@Override
	public void tick() {
		count ++;
		for (GameButton button : buttons)
		{
			button.unsetPointed();
		}
	//	if (point < buttons.size() && point >= 0)
		buttons.get(point).setPointed();
		//System.out.println(point);
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.addAll(buttons);
		return objects;
	}
	

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimotes[0].addWiiMoteEventListeners(this);
		
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
			point--;
			if (point < 0)
				point = buttons.size()-1;
		}
		else if (arg0.isButtonDownPressed())
		{
			point++;
			if (point >= buttons.size())
				point = 0;
		}
		else if (arg0.isButtonAPressed())
		{
			System.out.println("button " + point + " is pressed! Do something!");
		}
	}

}
