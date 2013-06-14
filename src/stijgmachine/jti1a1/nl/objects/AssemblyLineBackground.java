package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;
import stijgmachine.jti1a1.nl.model.AssemblyLineLogic;

/**
 * Write a description of class Background here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineBackground extends GameObject {

    private BufferedImage assemblyLineImage = null;
    private AssemblyLineBox box = new AssemblyLineBox();
    
    private int endCollision;
    private int endRounds;
    private static int collision;
    
	public AssemblyLineBackground() {
		super(0,0,0);
        changeImage(0);
	}
	
    public void drawImage(Graphics2D g) {
        g.drawImage(assemblyLineImage,0,0,Main.resX,Main.resY, null);
    }
    
    public void drawCol(Graphics2D g) {
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial - BOLD",28,28));
        if(box.rounds() < 6) {
            g.drawString("Botsingen: "+collision,100,200);
            endCollision = collision;
        } else {
            g.drawString("Aantal botsingen: "+endCollision,100,200);
        }
    }
    
    public void drawRounds(Graphics2D g) {
        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial - BOLD",28,28));
        if(box.rounds() < 6) {
            g.drawString("Ronde: "+box.rounds(),100,240);
            endRounds = box.rounds();
        } else {
            g.drawString("Aantal rondes: "+ endRounds,100,240);
        }
    }
    
    public void countCollision() {
    	collision += 1;
    }
    
    public void changeImage(int change) {
        try {
            switch(change) {
                case 0 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine01.bmp")); break;
                case 1 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine02.bmp")); break;
                case 2 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine03.bmp")); break;
                case 3 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine04.bmp")); break;
                case 4 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine05a.bmp")); break;
                case 5 : assemblyLineImage = ImageIO.read(getClass().getResource("../images/Bg_AssemblyLine05b.bmp")); break;
            }
        } catch(IOException e) {
            System.out.println(e);
        }     
    }
	
	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
		drawImage(g);
		drawCol(g);
		drawRounds(g);
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
