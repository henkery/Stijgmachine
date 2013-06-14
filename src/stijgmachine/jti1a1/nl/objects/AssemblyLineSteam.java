package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;


/**
 * Write a description of class Steam here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineSteam extends GameObject
{
    
    private ArrayList<AssemblyLineParticle> particleList = new ArrayList<AssemblyLineParticle>();
    private int x;
    private int y;
    private boolean asUpdate = true;
    private static boolean asRound = true;
    
    public AssemblyLineSteam() { 
    	super(0,0,0);
    }
    
    public AssemblyLineSteam(int x, int y) {
    	this();
        this.x = x;
        this.y = y;
        addParticle();
    }
    
    public void addParticle() {
        for(int i = 0; i < 200; i++)
            particleList.add(new AssemblyLineParticle(x,y,255));
    }
    
    public void setRound(boolean asRound) {
        this.asRound = asRound;
    }
    
    public void update() {   
        if(asRound == true) {
 
            for(AssemblyLineParticle p : particleList) {
                if(p.getAlpha() < 50) {
                    p.setX(x + 11);
                    p.setY(y + 40); 
                    p.setT(0);
                    p.setAlpha(255);
                } else {
                    p.update();
                }
            }   
            
            if(asUpdate == true) {
                if(y < 390) {
                    y+=5;
                } else {
                    asUpdate = false;
                    for(AssemblyLineParticle p : particleList)
                        p.setDirection(0);
                }
            } else {
                if(y > 200){
                    y-=5;
                    } else {
                    asUpdate = true;
                    for(AssemblyLineParticle p : particleList)
                        p.setDirection(1);
                }
            }
            
        } else {
            
            for(AssemblyLineParticle p : particleList) {
                if(p.getAlpha() < 50) {
                    p.setX(x + 30);
                    p.setY(y + 25); 
                    p.setT(0);
                    p.setAlpha(255);
                } else {
                    p.update();
                }
            }  
            
            if(asUpdate == true) {
                if(y < 370) {
                    y += 5;
                    x -= 3;
                } else {
                    asUpdate = false;
                    for(AssemblyLineParticle p : particleList)
                        p.setDirection(2);
                }
            } else { 
                if(y > 200) {
                    y -= 5;  
                    x += 3;
                } else {
                    asUpdate = true; 
                    for(AssemblyLineParticle p : particleList)
                        p.setDirection(3);
                }
            }
        }
    } 

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
        for(AssemblyLineParticle p : particleList)
            p.drawParticle(g);
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
