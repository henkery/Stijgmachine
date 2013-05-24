package stijgmachine.jti1a1.nl.GamePower;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import stijgmachine.jti1a1.nl.model.MiniGameLogic;
import stijgmachine.jti1a1.nl.objects.GameCursor;
import stijgmachine.jti1a1.nl.objects.GameObject;
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
	private Body bal = new Body(ball,25);
	private ArrayList<Body> obstacles = new ArrayList<Body>();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private GameCursor cursor = new GameCursor();
	private ArrayList<Point> points = new ArrayList<Point>();
	private StaticBody box = new StaticBody("Containement box",new Box(50,50,100,300,GameObject.RELATIVE_FROM_TOPLEFT));
	private boolean pressed = false;
	
	public GamePowerLogic()
	{
		wereld.add(bal);
		objects.add(bal);
		bal.setPosition(50,50);
//		wereld.add(box);
		addObstacles();
		objects.add(cursor);
	}
	
	public void addObstacles()
	{
		StaticBody obstacle1 = new StaticBody("Obstacle1", new Box(10f,300f,400,0,GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody obstacle2 = new StaticBody("Obstacle2", new Box(300,10,0,400,GameObject.RELATIVE_FROM_TOPLEFT));
		
		StaticBody obstacle3 = new StaticBody("Line", new Line(0, 200, 100, 200, GameObject.RELATIVE_FROM_TOPLEFT));
		
		wereld.add(obstacle1);
		wereld.add(obstacle2);
		wereld.add(obstacle3);
		
		
		
		objects.add(obstacle1);
		objects.add(obstacle2);
		objects.add(obstacle3);
	}
	
	public void makeLine()
	{
		if(points.size() > 0 )
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
			if(pressed == false)
			points.clear();
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
	
	public void resetBall()
	{
		wereld.add(box);
		bal.setPosition(50,50);
		wereld.remove(box);
		
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
		if(pressed == true)
		{
			points.add(new Point(event.getAx(),event.getAy()));
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
		makeLine();
//		cursor.update(cursorPos.x, cursorPos.y);
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
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

}
