package stijgmachine.jti1a1.nl.objects;

import stijgmachine.jti1a1.nl.controller.Main;

public class GameMenuResolutionSelector extends GameMenuSelector {

	public GameMenuResolutionSelector(int x, int y,	int relativeTo) {
		super(new String[]{"800x600", "1280x720", "1366x768", "1920x1080"}, x, y, relativeTo);
	}
	
	public void click() {
		switch (select) {
			case (0):
				Main.resX = 800;
				Main.resY = 600;
				break;
			case (1):
				Main.resX = 1280;
				Main.resY = 720;
				break;
			case (2):
				Main.resX = 1366;
				Main.resY = 768;
				break;
			case (3):
				Main.resX = 1920;
				Main.resY = 1080;
				break;
		}
		Main.callResize();
	}

}
