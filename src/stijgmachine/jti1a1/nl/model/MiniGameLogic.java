package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.*;

public abstract class MiniGameLogic {

	public abstract void tick();

	public abstract boolean isDone();
	
	public abstract ArrayList<GameObject> getObjects();

}
