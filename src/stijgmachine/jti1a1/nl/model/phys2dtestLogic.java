package stijgmachine.jti1a1.nl.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.Timer;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

import stijgmachine.jti1a1.nl.objects.GameObject;
import stijgmachine.jti1a1.nl.objects.phys2d.Body;
import stijgmachine.jti1a1.nl.objects.phys2d.Box;
import stijgmachine.jti1a1.nl.objects.phys2d.Circle;
import stijgmachine.jti1a1.nl.objects.phys2d.Line;
import stijgmachine.jti1a1.nl.objects.phys2d.StaticBody;
import stijgmachine.jti1a1.nl.objects.phys2d.World;
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

public class phys2dtestLogic extends MiniGameLogic {

	private World wereld = new World(new Vector2f(0.0f, 600.0f),10,new QuadSpaceStrategy(20, 5));
	private ArrayList<Body> lijnen = new ArrayList<Body>();
	private ArrayList<Shape> lines = new ArrayList<Shape>();
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Line2D.Double> prevLine = new ArrayList<Line2D.Double>();
	private boolean pressed = false;
	private Point[] points2;
	private Body cirkel = new Body(new Circle(20.0f, 0, 0, GameObject.RELATIVE_FROM_TOPLEFT),2.0f);
	private StaticBody box = new StaticBody("Containement box",new Box(50.0f, 50.0f, 0, 0, GameObject.RELATIVE_FROM_TOPLEFT));
	private ArrayList<GameObject> list;
	@Override
	public ArrayList<GameObject> getObjects() {
		// TODO Auto-generated method stub
		
		
		return list;
	}

	@Override
	public void giveMotes(Wiimote[] wiimotes) {
		// TODO Auto-generated method stub
		
		box.setPosition(100.0f,100.0f);
		
		wereld.add(cirkel);
		cirkel.setPosition(150.0f,100.0f);
		
		list = new ArrayList<GameObject>();
		
		drawBodies();
		
		
		list.add(box);
		list.add(cirkel);
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		goal();
		wereld.step();
		
	}
	
	public void drawBodies()
	{
		StaticBody line1 = new StaticBody(new Line(650,650,650,700, GameObject.RELATIVE_FROM_TOPLEFT));
		StaticBody line2 = new StaticBody(new Line(650,700,700,700, GameObject.RELATIVE_FROM_TOPLEFT));		// Goal Basket
		StaticBody line3 = new StaticBody(new Line(700,700,700,650, GameObject.RELATIVE_FROM_TOPLEFT));
		
		StaticBody object1 = new StaticBody(new Box(10.0f,300.0f, 0, 0, GameObject.RELATIVE_FROM_TOPLEFT));
		object1.setPosition(400.0f, 150.0f);								//java tekent vanaf linksboven
		StaticBody object2 = new StaticBody(new Box(10.0f,300.0f, 0,0, GameObject.RELATIVE_FROM_TOPLEFT));			// phys2d tekent vanaf het midden
		object2.setPosition(200.0f, 550.0f);
		StaticBody object3 = new StaticBody(new Box(10.0f,300.0f,0,0, GameObject.RELATIVE_FROM_TOPLEFT));
		object3.setPosition(500.0f,550.0f);
		
		
		list.add(line1);
		list.add(line2);
		list.add(line3);
		wereld.add(line1);
		wereld.add(line2);
		wereld.add(line3);
//		
//		
		list.add(object1);
		list.add(object2);
		list.add(object3);
		wereld.add(object1);
		wereld.add(object2);
		wereld.add(object3);
	}
	
	public void makeLinePreview()
	{
		for(int i = 0; i < points.size()-10; i+=10)
		{
			Point p1 = points.get(i);
			Point p2 = points.get(i+10);
			
			prevLine.add(new Line2D.Double(p1, p2));
		}
	}
	
	public void drawLinePreview(Graphics2D g2)
	{
		for(int i = 0; i < prevLine.size();i++)
		{
			g2.draw(prevLine.get(i));
		}
		prevLine.clear();
	}
	
	public void makeLine2()
	{
		if(points.size() > 0 && pressed == false)
		{
			GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			for(int i = 0; i < points.size()-10;i+=10)
			{
				Point punt1 = points.get(i);
				Point punt2 = points.get(i+10);
				path.moveTo(punt1.x, punt1.y);
				path.lineTo(punt2.x, punt2.y);
			}
			path.closePath();
			
			AffineTransform tx = new AffineTransform();
			Shape vorm = tx.createTransformedShape(path);
			lines.add(vorm);
			
			addLines();
			
			clearPoints();
			
		}
	}
	
	public void addLines()
	{
		for(int i = 0; i < points.size()-10;i+=10)
		{
			Point punt1 = points.get(i);
			Point punt2 = points.get(i+10);
			
			Body line = new StaticBody("Line",new Line(punt1.x,punt1.y,punt2.x,punt2.y, GameObject.RELATIVE_FROM_TOPLEFT));
			lijnen.add(line);
			wereld.add(line);
		}
	}
	
	public void clearPoints()
	{
		points.clear();
	}
	
	public void addBox()
	{
		wereld.add(box);
	}
	
	public void removeBox()
	{
		wereld.remove(box);
	}
	

	public void wachten()
	{
		/*time1 = new Timer(1000/2,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				removeBox();
				time1.stop();
			}
		});
		time1.start();*/
		
	}
	
    private void initPoints(float radius,float xPos,float yPos)
    {
        int numberOfPoints = 360/5;
        points2 = new Point[numberOfPoints];
        double cx = (int)xPos;
        double cy = (int)yPos;
        double r = (int)radius;
        int count = 0;
        for(int theta = 0; theta < 360; theta+=5)
        {
            int x = (int)(cx + r * Math.cos(Math.toRadians(theta)));
            int y = (int)(cy + r * Math.sin(Math.toRadians(theta)));
            points2[count++] = new Point(x, y);
        }
    }
	
	public void removeAllLines()
	{
		for(int i = 0; i < lijnen.size();i++)
		{
			wereld.remove(lijnen.get(i));
		}
		lijnen.clear();
		lines.clear();
	}
	
	public void goal()
	{
		if(cirkel.getPosition().getX() < 680 && cirkel.getPosition().getX() > 640)
		{
			if(cirkel.getPosition().getY() < 680 && cirkel.getPosition().getY() >640)
			{
				System.out.println("FINISH");
			}
		}
		
		
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
		// TODO Auto-generated method stub
		
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

}
