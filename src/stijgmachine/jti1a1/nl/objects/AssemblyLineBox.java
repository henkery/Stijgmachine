package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;


import java.io.*;

/**
 * Write a description of class Box here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineBox extends GameObject
{
    public static int round = 0;
    private static int x = -80;
    private static int y;
    private static boolean asRound = false;
    private boolean asSwitch = false;
    private boolean asSteam = false;
    private AssemblyLineSteam steam = new AssemblyLineSteam();
    private BufferedImage box = null;
    
    public AssemblyLineBox() {
    	super(0,0,0);
    	y = Main.resY / 2;
        try {
            box = ImageIO.read(getClass().getResource("../images/Icon_Box.png"));
        } catch(IOException e) {
            System.out.println(e);
        }  
    }
    
    public void setBox(int x) {
        this.x = x;
    }

    public void right() {
        if(x < Main.resX) {
            x += 8; 
        } else {
                if(round < 4) {
                    if(asSteam == true) {
                        steam.setRound(asSteam);
                        asSteam = false;
                    } else {
                        steam.setRound(asSteam);
                        asSteam = true;
                    }
                    asRound = true;
                    x = -80;
                } else {
                    x = Main.resX;
                }
        }
    }
    
    public void left() {
        if(x > -80)
            x -= 8;
            else {
                if(round < 4) {
                    x = -80;  
                } else {
                    if(asSwitch == true) {
                        asRound = true;
                    }
                    x = Main.resX;
                    asSwitch = true;
                }
            }
    }
    
    public void setYPosition(int y) {
        int yPosition1 = (int) ((Main.resY / 768f) * 300f);
        int yPosition2 = (int) ((Main.resY / 768f) * 425f);
    	if(y > yPosition1 && y < yPosition2)
    		this.y = y;
    }
    
    public int rounds() {
        if(asRound == true) {
            round += 1;
            asRound = false;
        }
        return round;
    }
    
    public void setRound(boolean asRound) {
        this.asRound = asRound;
    }
    
    public Rectangle getBounds() {
        return (new Rectangle(x,y,80,44));
    }

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
        g.drawImage(box,x,y,80,44, null);	
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
