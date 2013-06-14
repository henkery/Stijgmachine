package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import stijgmachine.jti1a1.nl.controller.Main;

/**
 * Write a description of class Shelf here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineShelf extends GameObject
{

    private int x;
    private int y;
    private boolean asUpdate = true;
    private boolean asColor = true;
    private static boolean asDir = true; 
    
    public AssemblyLineShelf() {
    	super(0,0,0);
    }
    
    public AssemblyLineShelf(int x, int y, boolean asColor) {
    	this();
        this.x = x;
        this.y = y;
        this.asColor = asColor;  
    }
    
    public void right() {
        if(x < Main.resX + 100)
            x += 8;
            else 
            x = -30;
    }
    
    public void left() {
        if(x > -30)
            x -= 8;
            else 
            x = Main.resX + 100;  
    }
    
    public void update() {
    	if(asDir == true) {
    		right();
    	} else {
    		left();
    	} 	
    }
    
    public void setDirection(boolean asDir) {
    	this.asDir = asDir;
    }

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) { 
        if(asColor == true) {
            Color c1 = new Color(243,184,35);
            g.setColor(c1);
        } else {
            Color c2 = new Color(205,149,12);
            g.setColor(c2);
        }
        int yHeight = (int) ((Main.resY / 768f) * 150f);
        int yPosition = (int) ((Main.resY / 768f) * 315);
        AffineTransform af1 = new AffineTransform();
        AffineTransform af2 = new AffineTransform();
        
        af1.setToShear(-0.2,0.0);
        g.setTransform(af1);
        
        Rectangle2D shelf = new Rectangle2D.Double(x,yPosition,40,yHeight);
        g.fill(shelf);
        g.setColor(new Color(0,0,0));
        g.draw(shelf);	

        af2.setToShear(0.0,0.0);
        g.setTransform(af2);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}
    
}
