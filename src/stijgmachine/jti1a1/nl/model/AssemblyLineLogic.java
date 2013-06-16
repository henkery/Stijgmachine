package stijgmachine.jti1a1.nl.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.objects.AssemblyLineBackground;
import stijgmachine.jti1a1.nl.objects.AssemblyLineBox;
import stijgmachine.jti1a1.nl.objects.AssemblyLineCloud;
import stijgmachine.jti1a1.nl.objects.AssemblyLineMoveRocket;
import stijgmachine.jti1a1.nl.objects.AssemblyLineRotateRocket;
import stijgmachine.jti1a1.nl.objects.AssemblyLineShelf;
import stijgmachine.jti1a1.nl.objects.GameObject;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

/**
 * Write a description of class Logic here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineLogic extends MiniGameLogic {
    
	private ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
    private AssemblyLineBox box = new AssemblyLineBox();
    private AssemblyLineBackground background = new AssemblyLineBackground();
	
    private boolean asColor;
    private boolean asDir;
    private boolean asRocket = true;
    private boolean asSwitchObject = true;
    private boolean soundStarted = false;
    private int yDirection;
    private int collision;
    private int count;
    private int tick;
 
    public AssemblyLineLogic() {   	
//    	addSound();
    	addBackground();
    	addCloud();
    	addShelf();
    	addBox();
    	addRocket();
    }
    
    public void addSound() {
       try {
    	   Clip clip = AudioSystem.getClip();
    	   AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("../sounds/MachineSound.wav"));
    	   clip.open(inputStream);
    	   clip.start(); 
    	   clip.loop(Clip.LOOP_CONTINUOUSLY);
       } catch (Exception e) {
    	   System.out.println(e.getMessage());
       }                 
    }
    
    public void addExplosionSound() {
        try {
     	   Clip clip = AudioSystem.getClip();
     	   AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResource("../sounds/ExplosionSound.wav"));
     	   clip.open(inputStream);
     	   clip.start(); 
        } catch (Exception e) {
     	   System.out.println(e.getMessage());
        } 
    }

    public void addShelf() {
        for(int x = -30; x < Main.resX + 100; x+=30) {
           if(asColor == true) {
        	   gameObject.add(new AssemblyLineShelf(x,(Main.resY / 2) - 50,true)); 
               asColor = false;
           } else {
        	   gameObject.add(new AssemblyLineShelf(x,Main.resY / 2 - 50,false)); 
               asColor = true;
           }
        }
    }
    
    public void addRocket() {
        getYDirection();
        if(asRocket == true) {
            for(int x = getXDirection(); x < Main.resX-50; x+=getXDirection())
            	 gameObject.add(new AssemblyLineMoveRocket(x,yDirection));
            asRocket = false;
        } else {
            if(asSwitchObject == true) {
                for(int x = getXDirection(); x < Main.resX-50; x+=getXDirection())
                	 gameObject.add(new AssemblyLineRotateRocket(x,yDirection));
                asSwitchObject = false;
            } else {
                for(int x = getXDirection(); x < Main.resX-50; x+=getXDirection())
                	 gameObject.add(new AssemblyLineRotateRocket(x,yDirection));
                asSwitchObject = true;
            }
            asRocket = true;
        }
    }
    
    public void addBox() {
    	 gameObject.add(new AssemblyLineBox());
    }
    
    public void addBackground() {
    	 gameObject.add(new AssemblyLineBackground());
    }
    
    public void addCloud() {
    	for(int i = 0; i < 500; i++) {
            gameObject.add(new AssemblyLineCloud());
        }
    }
    
    public int getXDirection() {
        return Main.resX / (2 + box.rounds());  
    }
    
    public void getYDirection() {
        Random r = new Random();
        yDirection = r.nextInt(450 - 350) + 350;
    }
    
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		// TODO Auto-generated method stub
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
	public void onExpansionEvent(ExpansionEvent event) {
		NunchukEvent nunchuck = (NunchukEvent)event;
		NunchukButtonsEvent buttons = nunchuck.getButtonsEvent();
			if(buttons.isButtonZeHeld() == true)
				asDir = true;
			else
				asDir = false;
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
	public void onIrEvent(IREvent event) {
		int y = (int)event.getAy();
		box.setYPosition(y);
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		System.out.println("Nunchuk is aangesloten!");
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		System.out.println("Nunchuk is ontkoppeld!");
	}

	@Override
	public void onStatusEvent(StatusEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void tick() {
		if(!soundStarted)
		{
			addSound();
			soundStarted = true;
		}
		if(tick != 10) {
			tick+=2;
		} else {
			tick = 0;
	      changeRound();
	        checkCollision();  
	    	for(GameObject o : gameObject) {
	    		if(o.getClass() == AssemblyLineMoveRocket.class)
	    			((AssemblyLineMoveRocket) o).update();
	    		if(o.getClass() == AssemblyLineRotateRocket.class)
	    			((AssemblyLineRotateRocket) o).update();
	    		if(o.getClass() == AssemblyLineCloud.class)
	    			((AssemblyLineCloud)o).update();
	    		if(asDir == true) {
	    			if(o.getClass() == AssemblyLineShelf.class)
	    				((AssemblyLineShelf)o).left();
	    			if(o.getClass() == AssemblyLineBox.class) 
	    				((AssemblyLineBox)o).left();		
	    		} else {
	    			if(o.getClass() == AssemblyLineShelf.class)
	    				((AssemblyLineShelf)o).right();
	    			if(o.getClass() == AssemblyLineBox.class) 
	    				((AssemblyLineBox)o).right();		
	    		}
	    	}
		}
	}

	@Override
	public boolean isDone() {
		return AssemblyLineBox.asRound;
	}

	@Override
	public ArrayList<GameObject> getObjects() {
		return gameObject;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();
	}

	public void clearList() {
		gameObject.clear();
	}
    
    public void changeRound() {

    			if(count < 6) {
    				if(AssemblyLineBox.round > count) {
    					count = AssemblyLineBox.round;
    					
    					gameObject.iterator();
    					Iterator it = gameObject.iterator();
    					while(it.hasNext()) {
    						Object object = it.next();
    						
    						if(object.getClass() == AssemblyLineBackground.class) {
    							((AssemblyLineBackground)object).changeImage(AssemblyLineBox.round);
    						}
    						if(object.getClass() == AssemblyLineMoveRocket.class) {
    							it.remove();
    						}
    						if(object.getClass() == AssemblyLineRotateRocket.class) {
    							it.remove();
    						}
    					}
    					addRocket();
    					if(AssemblyLineBox.round > 3) {
    						asDir = true;
    					}
    				} 
        	} else {
        		gameObject.iterator();
        		Iterator it = gameObject.iterator();
        		while(it.hasNext()) {
        			Object object = it.next();
        			if(object.getClass() == AssemblyLineRotateRocket.class)
        				it.remove();
        			if(object.getClass() == AssemblyLineMoveRocket.class)
        				it.remove();
        		}
           }
    }

    public void checkCollision() {
      for(GameObject o1 : gameObject) {
    	  if(o1.getClass() == AssemblyLineMoveRocket.class) {
          Rectangle r1 = ((AssemblyLineMoveRocket)o1).getBounds();
          Rectangle r2 = box.getBounds();
          if (r1.intersects(r2)){
        	  addExplosionSound();
        	  if(AssemblyLineBox.round > 3 && AssemblyLineBox.round < 6)
        		  box.setBox(Main.resX);
        	  else
        		  box.setBox(0);
              background.countCollision();
          } 
        }
      }
      
      for(GameObject o2 : gameObject) {
    	  if(o2.getClass() == AssemblyLineRotateRocket.class) {
    	     Rectangle r3 = ((AssemblyLineRotateRocket)o2).getBounds();
             Rectangle r4 = box.getBounds();
          	   if (r3.intersects(r4)) {
          		 addExplosionSound();
             	  if(AssemblyLineBox.round > 3 && AssemblyLineBox.round < 6)
            		  box.setBox(Main.resX);
            	  else
            		  box.setBox(0);
                 background.countCollision();
          	 } 
          }
        }
      }
    
    public int getCollision() {
    	return collision;
    }
	
}