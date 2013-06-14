package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;

import java.awt.geom.Rectangle2D;
import java.io.*;

/**
 * Write a description of class MoveRocket here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineMoveRocket extends GameObject {
    
    private int x;
    private int y;
    private boolean asUpdate = true;
    private BufferedImage rocketUp = null;
    private BufferedImage rocketDown = null;
    
    public AssemblyLineMoveRocket() {
		super(0,0,0);
        try {
            rocketUp = ImageIO.read(getClass().getResource("../images/rocketIconUp.png"));
            rocketDown = ImageIO.read(getClass().getResource("../images/rocketIconDown.png"));
        } catch(IOException e) {
            System.out.println(e);
        } 
    }
 
    public AssemblyLineMoveRocket(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    
    public void update() {
    	int yPosition1 = (int) ((Main.resY / 768f) * 500f);
    	int yPosition2 = (int) ((Main.resY / 768f) * 200f);
        if(asUpdate == true) {
            if(y < yPosition1)
                y+=5;
            else 
                asUpdate = false;
        } else {
            if(y > yPosition2)
                y-=5;
            else 
                asUpdate = true;
        }
    }
    
    public Rectangle getBounds() {
        return (new Rectangle(x,y,31,80));
    }

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
	        if(asUpdate == false) {
	            g.drawImage(rocketUp  ,x,y,31,80, null);
	        }
	        else {
	            g.drawImage(rocketDown,x,y,31,80,null);
	        }
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
