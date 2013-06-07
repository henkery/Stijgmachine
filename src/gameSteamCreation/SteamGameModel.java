package gameSteamCreation;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
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


public class SteamGameModel extends MiniGameLogic
{
	private static boolean gameStarted = false;
	private static boolean coalShoveled = false;
	private static boolean coalMove = false;
	private static boolean lightCoal = false;
	private static boolean heatWheelMove = false;
	private static boolean steamWheelMove = false;
	private static boolean shovel = false;
	private static Image heatWheelImg;
	private static Image coalFiredImg;
	private static Image coalPileImg; 
	private static Image redWarninglight;
	private static Image greenWarningLight;
	private static Image lighter;
	private static BufferedImage wheelSprite = null;
	private static BufferedImage wheelImg;
	private static int counter = 0;
	private static int xIndex;
	private static int yIndex;
	private static int index = 0;
	private static int stopHeat = 0;
	private static int stopSteam = 0;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Cursor cursor;
	private GameText text;
	
	
	public SteamGameModel()
	{	
		setGameStarted(true);
		setShovel(true);
		heatWheelImg = Toolkit.getDefaultToolkit().createImage("images/heatWheelSmall.png");
		coalFiredImg = Toolkit.getDefaultToolkit().createImage("images/coalsFired.png");
		coalPileImg = Toolkit.getDefaultToolkit().createImage("images/coalsFireplace.png");
		redWarninglight = Toolkit.getDefaultToolkit().createImage("images/warning-lamp-red.png");
		greenWarningLight = Toolkit.getDefaultToolkit().createImage("images/warning-lamp-green.png");
		lighter = Toolkit.getDefaultToolkit().createImage("images/lighterFloorImg.png");
		try{
			wheelSprite = ImageIO.read(new File("images/spriteturningwheel.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		cursor = new Cursor(0, 0, GameObject.ABSOLUTE);
		text = new GameText(0, 0, GameObject.ABSOLUTE);
		objects.add(cursor);
		objects.add(text);
	}
	
	public static void update()
	{
		xIndex = (index % 12)* 540;
		yIndex = 0;
		index++;
	}
	
	public static void updateStopSteam(int i)
	{
		stopSteam += i;
	}
	
	public static void updateStopHeat(int i)
	{
		stopHeat+=i;
	}
	
	public static int getStopSteam()
	{
		return stopSteam;
	}
	
	public static int getStopHeat()
	{
		return stopHeat;
	}
	
	public static void setWheelImg()
	{
		wheelImg = wheelSprite.getSubimage(xIndex, yIndex, 540, 553);
	}
	
	public static BufferedImage getWheelImg()
	{
		setWheelImg();
		return wheelImg;
	}
	
	public static void setCounter(int count)
	{
		counter = count;
	}
	
	public static int getCount()
	{
		return counter;
	}
	
	public static void setGameStarted(boolean start)
	{
		gameStarted = start;
	}
	
	public static boolean getGameStarted()
	{
		return gameStarted;
	}
	
	public static void setCoalShoveled(boolean shovel)
	{
		coalShoveled = shovel;
	}
	
	public static boolean getCoalShoveled()
	{
		return coalShoveled;
	}
	
	public static boolean coalMoved()
	{
		return coalMove;
	}
	
	public static boolean coalLit()
	{
		return lightCoal;
	}
	
	public static boolean steamWheelMoved()
	{
		return steamWheelMove;
	}
	
	public static void setCoalMoved(boolean moved)
	{
		coalMove = moved;
	}
	
	public static boolean getCoalMoved()
	{
		return coalMove;
	}
	
	public static void setCoalLit(boolean lit)
	{
		lightCoal = lit;
	}
	
	public static void setHeatWheelMoved(boolean turned)
	{
		heatWheelMove = turned;
	}
	
	public static void setSteamWheelMoved(boolean turned)
	{
		steamWheelMove = turned;
	}
	
	public static boolean getHeatWheelMoved()
	{
		return heatWheelMove;
	}
	
	public Image setBackImg(Image img)
	{
		img = Toolkit.getDefaultToolkit().createImage("images/SteamViewWithoutCoals.png");
		return img;
	}
	
	public Image getBackImgStart()
	{
		Image backimg = null;
		backimg = setBackImg(backimg);
		return backimg;
	}
	
	public static Image getBackImgShoveled()
	{
		return coalPileImg;
	}
	
	public static Image getHeatWheel()
	{
		return heatWheelImg;
	}
	
	public static Image getCoalFired()
	{
		return coalFiredImg;
	}
	
	public static Image getRedWarningLight()
	{
		return redWarninglight;
	}
	
	public static Image getGreenWarningLight()
	{
		return greenWarningLight;
	}
	
	public static Image getCoalPileImg()
	{
		return coalPileImg;
	}
	
	public static Image getLighter()
	{
		return lighter;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if(e.isButtonAPressed())
		{
			// HET OPPAKKEN VAN DE SCHOP
			if((cursor.x >= 1080 && cursor.x <= 1380) 
					&& (cursor.y >= 780 && cursor.y <= 980) 
					&& getGameStarted() 
					&& getCount() == 0)
			{
				setCounter(1);
			}
			// HET OPPAKKEN VAN DE KOLEN
			else if((cursor.x >= 1260 && cursor.x <= 1560) 
					&& (cursor.y >= 670 && cursor.y <= 870) 
					&& getCount() == 1) 
			{
				setCoalShoveled(true);
				setCounter(2);
			}
			//HET NEERLEGGEN VAN DE KOLEN
			else if((cursor.x >= 710 && cursor.x <= 900) 
					&& (cursor.y >= 620 && cursor.y <= 820)
					&& getCoalShoveled()
					&& getCount() == 2)
			{
				setCoalMoved(true);
				setCounter(3);
			}
				
			// HET OPPAKKEN VAN DE AANSTEKER
			else if((cursor.x >= 1000 && cursor.x <= 1050) 
					&& (cursor.y >= 780 && cursor.y <= 830) 
					&& getCount() == 3)
			{
				setCounter(4);
				System.out.println(getCoalMoved());
			}

			// HET AANSTEKEN VAN DE KOLEN
			else if((cursor.x >= 710 && cursor.x <= 900) 
					&& (cursor.y >= 620 && cursor.y <= 820 ) 
					&& getCoalMoved() 
					&& getCount() == 4)
			{
				setCoalLit(true);
				setCounter(5);
			}
			
			// HET OPPAKKEN VAN DE MOERSLEUTEL
			else if((cursor.x >= 710 && cursor.x <= 900) 
					&& (cursor.y >= 620 && cursor.y <= 820)  
					&& getCount() == 5)
			{
				setCounter(6);
			}
			
			// HET DRAAIEN VAN DE EERSTE WIEL
			else if((cursor.x >= 640 && cursor.x <= 690) 
					&& (cursor.y >= 430 && cursor.y <= 480) 
					&& coalLit() 
					&& getCount() == 6)
			{
				setHeatWheelMoved(true);
				setCounter(7);
			}
			// HET DRAAIEN VAN DE TWEEDE WIEL
			else if((cursor.x >= 580 && cursor.x <= 660) 
					&& (cursor.y >= 265 && cursor.y <= 345) 
					&& getHeatWheelMoved() 
					&& getCount() == 7)
			{
				setSteamWheelMoved(true);
				setCounter(8);
			}
			
		}
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0)
	{
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0)
	{
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0)
	{
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0)
	{
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		cursor.update(e.getAx(), e.getAy());
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
	}

	@Override
	public void tick()
	{
		update();
	}

	@Override
	public boolean isDone()
	{
		return false;
	}

	@Override
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes)
	{
		wiimotes[0].addWiiMoteEventListeners(this);
		wiimotes[0].activateIRTRacking();	
	}

	public static void setShovel(boolean b)
	{
		shovel = b;
	}
	
	public static boolean getShovel()
	{
		return shovel;
	}
}
