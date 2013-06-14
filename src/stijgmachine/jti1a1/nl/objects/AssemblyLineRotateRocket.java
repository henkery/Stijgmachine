package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.io.*;
import javax.swing.*;

import stijgmachine.jti1a1.nl.controller.Main;

/**
 * Write a description of class RotateRocket here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineRotateRocket extends GameObject
{
    
    private int x;
    private int y;
    private boolean asUpdate = true;
    private BufferedImage rocketUpRight = null;
    private BufferedImage rocketDownLeft = null;
    
    public AssemblyLineRotateRocket() {
    	super(0,0,0);
        try {
            rocketUpRight = ImageIO.read(getClass().getResource("../images/rocketIconUpRight.png"));
            rocketDownLeft = ImageIO.read(getClass().getResource("../images/rocketIconDownLeft.png"));
        } catch(IOException e) {
            System.out.println(e);
        }    
    }
 
    public AssemblyLineRotateRocket(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public void update() {
    	int yPosition1 = (int) ((Main.resY / 768f) * 500f);
    	int yPosition2 = (int) ((Main.resY / 768f) * 200f);
        if(asUpdate == true) {
           if(y < yPosition1) {
               y += 5;
               x -= 3;
           } else
               asUpdate = false;
        } else { 
           if(y > yPosition2) {
               y -= 5;  
               x += 3;
           } else
               asUpdate = true;         
        }
    }
    
    public Rectangle getBounds() {
        return (new Rectangle(x,y,66,64));
    }
 
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
        if(asUpdate == false)
            g.drawImage(rocketUpRight,x,y,66,64,null);
        else
            g.drawImage(rocketDownLeft,x,y,66,64,null);
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