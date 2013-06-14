package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import stijgmachine.jti1a1.nl.controller.Main;

public class AssemblyLineCloud extends GameObject {

    private boolean asBall;
    private int time;
    private int x;
    private int y; 
    private int alpha;
    
    public AssemblyLineCloud() {
    	super(0,0,0);
        Random r1 = new Random();
        x = r1.nextInt(Main.resX);
        Random r2 = new Random();
        y = r2.nextInt(80 - -20) + -20;
        Random r3 = new Random();
        alpha = r3.nextInt(100 - 0) + 0;
    }
    
    public void update() {
        x++;
        if(x > Main.resX)
        x = -60;
        if(time < 105) {
            time++;
            asBall = true;
        } else
            asBall = false;
    }

	@Override
	public void draw(Graphics2D g, int height, int width, int x2, int y2) {
        g.setColor(new Color(150,150,150,alpha));
        g.fill(new Ellipse2D.Double(x,y,60,60));  
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
