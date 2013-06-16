package stijgmachine.jti1a1.nl.GamePower;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.GrappleCursor;
import stijgmachine.jti1a1.nl.objects.phys2d.Body;
import stijgmachine.jti1a1.nl.objects.phys2d.Circle;
import stijgmachine.jti1a1.nl.objects.phys2d.Line;
import stijgmachine.jti1a1.nl.objects.phys2d.Shape;
import stijgmachine.jti1a1.nl.objects.phys2d.StaticBody;
import stijgmachine.jti1a1.nl.objects.phys2d.World;
import stijgmachine.jti1a1.nl.objects.phys2d.Box;
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

public class GamePowerLogic extends MiniGameLogic
{
	private World wereld = new World(new Vector2f(0.0f, 600.0f),10,new QuadSpaceStrategy(20, 5));
	private GamePowerBall ball = new GamePowerBall(25,100,300,GameObject.RELATIVE_FROM_TOPLEFT);
	private Body bal = new Body(ball,10);
	private ArrayList<Body> obstacles = new ArrayList<Body>();
	private ArrayList<Body> prevLines = new ArrayList<Body>();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private GameCursor cursor = new GrappleCursor();
	private ArrayList<Point> points = new ArrayList<Point>();
	private StaticBody box = new StaticBody("Containement box",new Box(50,50,100,300,GameObject.RELATIVE_FROM_TOPLEFT));
	private boolean pressed = false;
	private int tickCounter = 0;
	private boolean countTicks = false;
	private boolean finish = false;
	private GamePowerEndText endText = new GamePowerEndText(50,50,GameObject.RELATIVE_FROM_TOPLEFT,"Game Completed",36,"Old English Text MT");
	private GamePowerText instructie = new GamePowerText(850, 0, GameObject.RELATIVE_FROM_TOPLEFT, "Het Doel van het spel is de energie bal\nIn de socket te krijgen,\nDit doe je doormiddel van een lijn te tekenen met je cursor\nHoud A ingedrukt om een lijn te tekenen en druk op B op de bal te resetten\nDruk op het bovenste pijltje om de lijnen te verwijderen", 16, "Copperplate Gothic Bold");
	private boolean addOnce = false;
	private GamePowerBackGround back = new GamePowerBackGround(0,0,GameObject.RELATIVE_FROM_TOPLEFT,"",1);
	private Wiimote mote;
	private int tickCounter2 = 0;
	private Clip clip;
	
	public GamePowerLogic()
	{
		
		try {
			getAudio();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		objects.add(back);
		wereld.add(bal);
		bal.setPosition(50,50);
//		wereld.add(box);
		addObstacles();
		objects.add(cursor);
		objects.add(bal);
		objects.add(instructie);
		
	}
	
	public void getAudio() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = (Clip)AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("src/res/GamePowerTheme4.wav")).getFormat()));
		clip.open(AudioSystem.getAudioInputStream(new File("src/res/GamePowerTheme4.wav")));	
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}


	
	public void addObstacles()
	{
		StaticBody obstacle1 = new StaticBody("Obstacle1", new Box(10,300,400,0,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle2 = new StaticBody("Obstacle2", new Box(300,10,0,400,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle3 = new StaticBody("Obstacle3", new Box(10,500,400,500,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle4 = new StaticBody("Obstacle4", new Box(10,200,200,0,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle5 = new StaticBody("Obstacle5", new Box(10,600,200,500,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle6 = new StaticBody("Obstacle6", new Box(200,10,495,255,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle7 = new StaticBody("Obstacle7", new Box(200,10,700,350,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle8 = new StaticBody("Obstacle8", new Box(400,10,600,550,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle9 = new StaticBody("Obstacle9", new Box(10, 355, 800, 175, GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle10 = new StaticBody("Obstacle10", new Box(400,10,950,550,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle11 = new StaticBody("Obstacle11", new Box(710,10,1655,550,GameObject.RELATIVE_FROM_TOPLEFT));
		obstacle1.setPosition(400, 0);
		obstacle2.setPosition(0, 400);
		obstacle3.setPosition(400, 500);
		obstacle4.setPosition(200, 0);
		obstacle5.setPosition(200, 500);
		obstacle6.setPosition(495, 255);
		obstacle7.setPosition(700, 350);
		obstacle8.setPosition(600, 550);
		obstacle9.setPosition(800, 175);
		obstacle10.setPosition(950, 550);
		obstacle11.setPosition(1655, 550);
		
//		StaticBody obstacle3 = new StaticBody("Line", new Line(0, 200, 100, 200, GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody basketLine1 = new StaticBody("g1", new Line(1200,700,1200,750,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody basketLine2 = new StaticBody("g2", new Line(1200,750,1250,750,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody basketLine3 = new StaticBody("g3", new Line(1250,750,1250,700,GameObject.RELATIVE_FROM_TOPLEFT));
		
		StaticBody gameContainer = new StaticBody("GameContainer", new Box(4000,2000,0,0,GameObject.RELATIVE_FROM_TOPLEFT));
		gameContainer.setPosition(0, 0);
		
		wereld.add(gameContainer);
		
		
		wereld.add(basketLine1);
		wereld.add(basketLine2);
		wereld.add(basketLine3);
		
		wereld.add(obstacle1);
		wereld.add(obstacle2);
		wereld.add(obstacle3);
		wereld.add(obstacle4);
		wereld.add(obstacle5);
		wereld.add(obstacle6);
		wereld.add(obstacle7);
		wereld.add(obstacle8);
		wereld.add(obstacle9);
		wereld.add(obstacle10);
		wereld.add(obstacle11);
		
		
		
		objects.add(basketLine1);
		objects.add(basketLine2);
		objects.add(basketLine3);
		
		objects.add(obstacle1);
		objects.add(obstacle2);
		objects.add(obstacle3);
		objects.add(obstacle4);
		objects.add(obstacle5);
		objects.add(obstacle6);
		objects.add(obstacle7);
		objects.add(obstacle8);
		objects.add(obstacle9);
		objects.add(obstacle10);
		objects.add(obstacle11);
	}
	
	public void goal()
	{
		if(bal.getPosition().getX() > 1200 && bal.getPosition().getX() < 1250)
		{
			if(bal.getPosition().getY() > 700 && bal.getPosition().getY() < 750)
			{
				finish = true;
			}
		}
	}
	
	public void drawFinishWords()
	{
		if(finish)
		{
			if(addOnce == false)
			{
				objects.add(endText);
				addOnce = true;
				isDone();
			}
		}
		
		
		if(!finish)
		{
			addOnce = false;
			objects.remove(endText);
		}
	}
	
	public void makePrevLine()
	{
		if(points.size() > 10)
		{
			for(int i = 0; i < points.size()-10;i+=10)
			{
				Point p1 = points.get(i);
				Point p2 = points.get(i+10);
				
				StaticBody obstacle = new StaticBody(new GamePowerLine(p1.x,p1.y,p2.x,p2.y, GameObject.RELATIVE_FROM_TOPLEFT));
				
				prevLines.add(obstacle);
				objects.add(obstacle);
			}
		}
	}
	
	public void removePrevLine()
	{
		if(pressed == false)
		{		
			for(int i = 0; i < prevLines.size();i++)
			{
			objects.remove(prevLines.get(i));
			}
			prevLines.clear();
			
		}
	}
	
	public void makeLine()
	{
		if(points.size() > 10 && pressed == false)
		{
			for(int i = 0; i < points.size()-10;i+=10)
			{
				Point p1 = points.get(i);
				Point p2 = points.get(i+10);
				
//				StaticBody obstacle = new StaticBody("Line",new Line(p1.x,p1.y,p2.x,p2.y,GameObject.RELATIVE_FROM_TOPLEFT));
				StaticBody obstacle = new StaticBody(new GamePowerLine(p1.x, p1.y, p2.x, p2.y, GameObject.RELATIVE_FROM_TOPLEFT));
				
				wereld.add(obstacle);
				objects.add(obstacle);
				obstacles.add(obstacle);
			}
			points.clear();
			removePrevLine();
		}
	}
	
	public void removeLines()
	{
		for(int i = 0; i < obstacles.size();i++)
		{
			int index = checkMatch(obstacles.get(i));
			if(index != -1)
			{
				objects.remove(index);
				wereld.remove(obstacles.get(i));
			}
		}
		obstacles.clear();
	}
	
	public int checkMatch(Body b)
	{
		for(int i = 0; i < objects.size();i++)
		{
			if(objects.get(i) == b)
			{
				return i;
			}
		}
		return -1;
	}
	
	public void refreshBall()
	{
		try
		{
			objects.remove(bal);
			objects.add(bal);
		}
		catch(ConcurrentModificationException ex)
		{
			System.out.println("");
		}
	}
	
	
	
	public void resetBall()
	{
		if(countTicks != true)
		{
			wereld.add(box);
		}
		
		bal.setPosition(50,50);
		box.setPosition(50, 50);
		countTicks = true;
		
	}
	
	
	@Override
	public void onButtonsEvent(WiimoteButtonsEvent event) 
	{
		if(event.isButtonAJustPressed())
		{
			pressed = true;
		}
		if(event.isButtonAJustReleased())
		{
			pressed = false;
		}
		if(event.isButtonUpJustPressed())
		{
			removeLines();
		}
		if(event.isButtonBJustPressed())
		{
			resetBall();
			finish = false;
		}
		if(event.isButtonHomeJustPressed())
		{
			System.exit(0);
		}

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
	public void onIrEvent(IREvent event) 
	{
//		cursorPos = new Point(event.getAx(),event.getAy());
		cursor.update(event.getAx(), event.getAy());
		back.updateCursorPos(new Point(cursor.x,cursor.y));
		if(pressed == true)
		{
			points.add(new Point(cursor.x,cursor.y));
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

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		
		wereld.step();
		makePrevLine();
		makeLine();
		goal();
		drawFinishWords();
//		refreshBall();
		tickCounter2++;
		if(tickCounter2 == 11000)
		{
//			clip.stop();
//			clip.start();
//			System.out.println("Test");
		}
		tickCounter2%=11000;
		
		if(countTicks)
		{
			tickCounter +=1;
		}
		if(!countTicks)
		{
			tickCounter = 0;
		}
		if(tickCounter == 100)
		{
			wereld.remove(box);
			countTicks = false;
		}
//		cursor.update(cursorPos.x, cursorPos.y);
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return finish;
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
		mote = wiimotes[0];
	}

}
