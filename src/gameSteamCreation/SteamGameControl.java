package gameSteamCreation;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.Timer;

import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class SteamGameControl extends JFrame implements WiimoteListener,ActionListener
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Wiimote	wiimote;
	private SteamGamePanel panel;
	private SteamGameModel model;
	private Timer timer = new Timer(1000/10,this);

	public SteamGameControl()
	{
		//SteamGameModel model = new SteamGameModel();
		System.out.println(SteamGameModel.getGameStarted());
		System.out.println(getGameStart());
		//getGameStarted();
		//SteamGamePanel panel = new SteamGamePanel();
		//panel.setGameStarted();
		//SteamGamePanel view = new SteamGamePanel();
		//SteamHandler handler = new SteamHandler();
		
		//panel.addMouseListener(handler);
		//panel.addMouseMotionListener(handler);
		
		timer.start();
	}
	
	public SteamGameControl(Wiimote w)
	{
		this.wiimote = w;
	}

	/*public static void main(String []args)
	{
//		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
//      Wiimote wiimote = wiimotes[0];
//      wiimote.activateIRTRacking();
//      wiimote.activateMotionSensing();
//      wiimote.addWiiMoteEventListeners(new SteamGameModel(wiimote));
		//SteamGameControl control = new SteamGameControl();
		SteamGameControl control = new SteamGameControl();
	}*/
	
	public static void setGameStart()
	{
		SteamGameModel.setGameStarted(true);
	}
	
	public static void setCoalShovel(boolean shovel)
	{
		SteamGameModel.setCoalShoveled(shovel);
	}
	
	public static boolean getCoalShovel()
	{
		return SteamGameModel.getCoalShoveled();
	}
	
	public static void setGameStart(boolean start)
	{
		SteamGameModel.setGameStarted(start);
	}
	
	public static boolean getGameStart()
	{
		return SteamGameModel.getGameStarted();
	}
	
	public Image getBackImgStart()
	{
		return model.getBackImgStart();
	}
	
	public static Image getBackImgShoveled()
	{
		return SteamGameModel.getBackImgShoveled();
	}
	
	public static Image getHeatWheelImage()
	{
		return SteamGameModel.getHeatWheel();
	}
	
	public static Image getCoalFiredImg()
	{
		return SteamGameModel.getCoalFired();
	}
	
	public static Image getLighter()
	{
		return SteamGameModel.getLighter();
	}
	
	public static void setCoalLit(boolean lit)
	{
		SteamGameModel.setCoalLit(lit);
	}
	public static boolean getCoalLit()
	{
		return SteamGameModel.coalLit();
	}
	
	public static void setHeatWHeelMoved(boolean moved)
	{
		SteamGameModel.setHeatWheelMoved(moved);
	}
	
	public static boolean getHeatWheelMoved()
	{
		return SteamGameModel.getHeatWheelMoved();
	}
	
	public static void setSteamWheelMoved(boolean moved)
	{
		SteamGameModel.setSteamWheelMoved(moved);
	}
	
	public static boolean getSteamWheelMoved()
	{
		return SteamGameModel.steamWheelMoved();
	}
	
	public static boolean getCoalMoved()
	{
		return SteamGameModel.getCoalMoved();
	}
	
	public static boolean getShovel()
	{
		return SteamGameModel.getShovel();
	}
	
	public static void setCount(int count)
	{
		SteamGameModel.setCounter(count);
	}
	
	public static int getCounter()
	{
		return SteamGameModel.getCount();
	}
	
	public static Image getRedWarning()
	{
		return SteamGameModel.getRedWarningLight();
	}
	
	public static Image getGreenWarning()
	{
		return SteamGameModel.getGreenWarningLight();
	}
	
	public static Image getCoalPileImage()
	{
		return SteamGameModel.getCoalPileImg();
	}
	
	public static int getStopSteam()
	{
		return SteamGameModel.getStopSteam();
	}
	
	public static int getStopHeat()
	{
		return SteamGameModel.getStopHeat();
	}
	
	public static void updateStopSteam(int i)
	{
		SteamGameModel.updateStopSteam(i);
	}
	
	public static void updateStopHeat(int i)
	{
		SteamGameModel.updateStopHeat(i);
	}
	
	public static void update()
	{
		SteamGameModel.update();
	}
	
	public static BufferedImage getWheelImg()
	{
		return SteamGameModel.getWheelImg();
	}
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0)
	{
		
	}

	@Override
	public void onClassicControllerInsertedEvent(
			ClassicControllerInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClassicControllerRemovedEvent(
			ClassicControllerRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIrEvent(IREvent arg0)
	{
		
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusEvent(StatusEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(SteamGameModel.getCount() == 4)
		{
			System.out.println("U heeft gewonnen!!!! :D ");
			timer.stop();
		}
	}

}
