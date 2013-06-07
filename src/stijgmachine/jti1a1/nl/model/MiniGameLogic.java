package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.GameObject;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.utils.WiiUseApiListener;
import wiiusej.wiiusejevents.utils.WiimoteListener;

public abstract class MiniGameLogic implements WiimoteListener
{

	public abstract void tick();

	public abstract boolean isDone();
	
	public abstract ArrayList<GameObject> getObjects();

	public WiimoteListener getActionListener() {
		// TODO Auto-generated method stub
		return (WiimoteListener) this;
	}

	public abstract void giveMotes(Wiimote[] wiimotes);

}
