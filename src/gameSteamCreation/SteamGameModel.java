package gameSteamCreation;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import stijgmachine.jti1a1.nl.controller.Main;
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


public class SteamGameModel extends MiniGameLogic implements ActionListener
{
	private static boolean gameStarted = false,coalShoveled = false,coalMove = false,
	lightCoal = false,heatWheelMove = false,steamWheelMove = false,shovel = false,gameDone = false;
	private static Image backgroundImg,heatWheelImg,coalFiredImg,
	coalPileImg,redWarninglight,greenWarningLight,lighter,fire; 
	private static BufferedImage wheelSprite = null,wheelImg;
	private static int counter = 0,xIndex,yIndex,index = 0,stopHeat = 0,stopSteam = 0;
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private Cursor cursor;
	private GameText text;
	private WaterSteam steam1,steam2,steam3,steam4;
	
	
	public SteamGameModel()
	{	 
		setGameStarted(true);
		setShovel(true);
		backgroundImg = Toolkit.getDefaultToolkit().createImage("images/SteamViewWithoutCoals.png");
		heatWheelImg = Toolkit.getDefaultToolkit().createImage("images/heatWheelSmall.png");
		coalFiredImg = Toolkit.getDefaultToolkit().createImage("images/coalsFired.png");
		coalPileImg = Toolkit.getDefaultToolkit().createImage("images/coalsFireplace.png");
		redWarninglight = Toolkit.getDefaultToolkit().createImage("images/warning-lamp-red.png");
		greenWarningLight = Toolkit.getDefaultToolkit().createImage("images/warning-lamp-green.png");
		lighter = Toolkit.getDefaultToolkit().createImage("images/lighterFloorImg.png");
		fire = Toolkit.getDefaultToolkit().createImage("images/animatedfire.gif");
		try{
			wheelSprite = ImageIO.read(new File("images/spriteturningwheel.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		cursor = new Cursor(0, 0, GameObject.ABSOLUTE);
		text = new GameText(0, 0, GameObject.ABSOLUTE);
		steam1 = new WaterSteam(130,650,GameObject.ABSOLUTE);
		steam2 = new WaterSteam(170,650,GameObject.ABSOLUTE);
		steam3 = new WaterSteam(200,650,GameObject.ABSOLUTE);
		steam4 = new WaterSteam(230,650,GameObject.ABSOLUTE);
		
		objects.add(cursor);
		objects.add(text);
		
		Timer timer = new Timer(1000/100,this);
//		timer.start();
	}
	
	public static void update()
	{
		xIndex = (index % 12)* 540;
		yIndex = 0;
		index++;
	}
	
	public static int translatepixelX(int xWaarde)
	{
		return (int) ((Main.resX/1600)*xWaarde);
	}
	
	public static int translatepixelY(int yWaarde)
	{
		return (int)((Main.resY/900)*yWaarde);
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
	
	public static Image setBackImg(Image img)
	{
		img = Toolkit.getDefaultToolkit().createImage("images/SteamViewWithoutCoals.png");
		return img;
	}
	
	public static Image getBackImgStart()
	{
		Image backImg = null;
		backImg = setBackImg(backImg);
		if(backImg==null)
		System.out.println("geen plaatje jonguh");
		//return backImg;
		return backgroundImg;
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
	
	public static Image getFire()
	{
		return fire;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if(e.isButtonAPressed())
		{
			// HET OPPAKKEN VAN DE SCHOP
			if((cursor.x >= translatepixelX(1080) && cursor.x <= translatepixelX(1380)) 
					&& (cursor.y >= translatepixelY(780) && cursor.y <= translatepixelY(980)) 
					&& getGameStarted() 
					&& getCount() == 0)
			{
				setCounter(1);
			}
			// HET OPPAKKEN VAN DE KOLEN
			else if((cursor.x >= translatepixelX(1260) && cursor.x <= translatepixelX(1560)) 
					&& (cursor.y >= translatepixelY(670) && cursor.y <= translatepixelY(870)) 
					&& getCount() == 1) 
			{
				setCoalShoveled(true);
				setCounter(2);
			}
			//HET NEERLEGGEN VAN DE KOLEN
			else if((cursor.x >= translatepixelX(710) && cursor.x <= translatepixelX(900)) 
					&& (cursor.y >= translatepixelY(620) && cursor.y <= translatepixelY(820))
					&& getCoalShoveled()
					&& getCount() == 2)
			{
				setCoalMoved(true);
				setCounter(3);
			}
				
			// HET OPPAKKEN VAN DE AANSTEKER
			else if((cursor.x >= translatepixelX(1000) && cursor.x <= translatepixelX(1050)) 
					&& (cursor.y >= translatepixelY(780) && cursor.y <= translatepixelY(830)) 
					&& getCount() == 3)
			{
				setCounter(4);
				System.out.println(getCoalMoved());
			}

			// HET AANSTEKEN VAN DE KOLEN
			else if((cursor.x >= translatepixelX(710) && cursor.x <= translatepixelX(900)) 
					&& (cursor.y >= translatepixelY(620) && cursor.y <= translatepixelY(820)) 
					&& getCoalMoved() 
					&& getCount() == 4)
			{
				setCoalLit(true);
				setCounter(5);
			}
			
			// HET OPPAKKEN VAN DE MOERSLEUTEL
			else if((cursor.x >= translatepixelX(710) && cursor.x <= translatepixelX(900)) 
					&& (cursor.y >= translatepixelY(620) && cursor.y <= translatepixelY(820))  
					&& getCount() == 5)
			{
				setCounter(6);
			}
			
			// HET DRAAIEN VAN DE EERSTE WIEL
			else if((cursor.x >= translatepixelX(640) && cursor.x <= translatepixelX(690)) 
					&& (cursor.y >= translatepixelY(430) && cursor.y <= translatepixelY(480)) 
					&& coalLit() 
					&& getCount() == 6)
			{
				objects.add(steam1);
				setHeatWheelMoved(true);
				setCounter(7);
			}
			// HET DRAAIEN VAN DE TWEEDE WIEL
			else if((cursor.x >= translatepixelX(580) && cursor.x <= translatepixelX(660)) 
					&& (cursor.y >= translatepixelY(265) && cursor.y <= translatepixelY(345)) 
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
		for(GameObject g : objects)
			if(g.getClass() == Cursor.class)
				cursor = (Cursor)g;
				
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
		return gameDone;
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

	public static boolean getGameDone()
	{
		return gameDone;
	}

	public static void setGameDone(boolean b)
	{
		gameDone = b;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for(Iterator<Particle> itr = steam1.getSteam().iterator(); itr.hasNext();)
		{
			Particle p = itr.next();
			
			if(p.getY() < 600)
				itr.remove();
			else
				p.update();
		}
		
		if(steam1.getSteam().size() < 100)
			steam1.getSteam().add(new Particle());
	}
}
