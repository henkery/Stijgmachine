package stijgmachine.jti1a1.nl.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.GameButton;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameImage;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GrappleCursor;
import stijgmachine.jti1a1.nl.objects.NewCursor;
import stijgmachine.jti1a1.nl.objects.endgame.DraggableImage;
import stijgmachine.jti1a1.nl.objects.endgame.ObjectHolder;
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

public class EndGameLogic extends MiniGameLogic {
	
	private ArrayList<GameObject> objects;
	private NewCursor cursor;
	private DraggableImage dragging;
	private boolean block;
	private boolean reallyDone;
	
	public EndGameLogic () {
		block = false;
		reallyDone = false;
		objects = new ArrayList<GameObject>();
		try {
			objects.add(new GameImage(0, 0,Main.resX,Main.resY, GameObject.ABSOLUTE, ImageIO.read(getClass().getResource("/res/RocketBack.png"))));
			objects.add(new GameImage(scaleX(-270), scaleY(180),scaleX(560),scaleX(176), GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/platform.png"))));
			objects.add(new GameImage(scaleX(-150), scaleY(-348),scaleX(298),scaleX(641), GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/rocket.png"))));
			objects.add(new ObjectHolder(scaleX(-30), scaleY(-200), scaleX(60), scaleY(120), 8, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/door-holder.png")), ImageIO.read(getClass().getResource("/res/Rocket_Door_FIXED-with-holder.png"))));
			objects.add(new ObjectHolder(scaleX(40), scaleY(-20), scaleX(60), scaleY(60), 7, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/water-tank-holder-without-tank.png")), ImageIO.read(getClass().getResource("/res/water-tank-holder-with-tank.png"))));
			objects.add(new ObjectHolder(scaleX(-70), scaleY(50), scaleX(80), scaleY(30), 6, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/steam-tank-holder-without-tank.png")), ImageIO.read(getClass().getResource("/res/steam-tank-holder-with-tank.png"))));
			objects.add(new ObjectHolder(scaleX(-45), scaleY(-10), scaleX(50), scaleY(50), 9, GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/energy-holder.png")), ImageIO.read(getClass().getResource("/res/energy-holder-with-energy.png"))));
			objects.add(new DraggableImage(scaleX(0), scaleY(0), scaleX(60), scaleY(120), 8, GameObject.RELATIVE_FROM_TOPLEFT, ImageIO.read(getClass().getResource("/res/rocketDoorHeel.png"))));
			objects.add(new DraggableImage(scaleX(0), scaleY(0), scaleX(70), scaleY(20), 6, GameObject.RELATIVE_FROM_TOPLEFT, ImageIO.read(getClass().getResource("/res/Steam-tank.png"))));
			objects.add(new DraggableImage(scaleX(0), scaleY(0), scaleX(50), scaleY(50), 7, GameObject.RELATIVE_FROM_TOPLEFT, ImageIO.read(getClass().getResource("/res/wooden-water-tank.png"))));
			objects.add(new DraggableImage(scaleX(0), scaleY(0), scaleX(50), scaleY(50), 9, GameObject.RELATIVE_FROM_TOPLEFT, ImageIO.read(getClass().getResource("/res/New_Energy_Ball.png"))));
//			ImageIcon icon = new ImageIcon();//
			//icon.
//			objects.add(new ObjectHolder(0, 0, 30, 30, GameObject.RELATIVE_FROM_CENTER, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cursor = new NewCursor();
		objects.add(cursor);
	}
	

	@Override
	public ArrayList<GameObject> getObjects() {
		// TODO Auto-generated method stub
		return objects;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return reallyDone;
	}
	
	public boolean isReadyForAnimation() {
		for (GameObject o : objects) {
			if (o.getClass() == DraggableImage.class) {
				if (!((DraggableImage) o).isDone())
					return false;
			}
		}
		return true;
	}

	@Override
	public void tick() {
		if (block == false) {
			if (isReadyForAnimation()) {
				cursor.update(999999, 99999);
				block = true;
				objects = new ArrayList<GameObject>();
				try {
					objects.add(new GameImage(0, 0,Main.resX,Main.resY, GameObject.ABSOLUTE, ImageIO.read(getClass().getResource("/res/RocketBack.png"))));
					objects.add(new GameImage(scaleX(-270), scaleY(180),scaleX(560),scaleX(176), GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/platform.png"))));
					objects.add(new GameImage(scaleX(-150), scaleY(-348),scaleX(298),scaleX(641), GameObject.RELATIVE_FROM_CENTER, ImageIO.read(getClass().getResource("/res/rocket.png"))));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			playAnimationTick();
		}

	}



	private void playAnimationTick() {
		((GameImage)objects.get(2)).setY(((GameImage)objects.get(2)).getY()-1);
		if (((GameImage)objects.get(2)).getY() < Main.resY-(3*Main.resY))
			reallyDone = true;
		
	}


	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		if (!block) {
			if (arg0.isButtonAPressed()) {
				if (dragging == null) {
					for (GameObject o : objects) {
						if (o.getClass() == DraggableImage.class) {
							if (((DraggableImage) o).isWithin(cursor.x, cursor.y))
								dragging = (DraggableImage) o;
						}
					}
				}
				else {
					for (GameObject o : objects) {
						if (o.getClass() == ObjectHolder.class) {
							if (((ObjectHolder) o).isWithin(cursor.x, cursor.y) && ((ObjectHolder) o).idMatch(dragging.getid())) {
								o.click();
								dragging.finish();
							}
							
						}
					}
					dragging = null;
				}
			}
		}
//		
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
		if (!block) {
			cursor.update(arg0.getAx(), arg0.getAy());
			if (dragging != null)
				dragging.update(arg0.getAx(), arg0.getAy());
		}
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
	
	public int scaleY(int y) {
		return (int) (Main.resY/768f*y);
	}
	
	public int scaleX(int x) {
		return (int) (Main.resX/1366f*x);
	}

}
