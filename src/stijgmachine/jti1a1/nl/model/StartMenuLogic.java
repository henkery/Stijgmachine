package stijgmachine.jti1a1.nl.model;

import java.util.ArrayList;

import stijgmachine.jti1a1.nl.objects.GameButton;
import stijgmachine.jti1a1.nl.objects.GameObject;

public class StartMenuLogic extends MiniGameLogic {
	
	private GameButton button1;

	private int count;
	
	public StartMenuLogic() {
		count = 0;
		button1 = new GameButton(20, 20, 100, 100, "henk");
	}

	@Override
	public boolean isDone() {
		return (count > 100);
	}

	@Override
	public void tick() {
		count ++;
		
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		objects.add(button1);
		return objects;
	}

}
