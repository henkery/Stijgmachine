package stijgmachine.jti1a1.nl.objects;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Write a description of class Particle here.
 * 
 * @author Vincent Stout
 * @version 1.2
 */
public class AssemblyLineParticle
{
    
    private double x;
    private double y;
    private double t = 0;
    private double speed;
    private double angle;
    private int alpha;
    private boolean asUpdate = true;
    private int directionNr;
    
    public AssemblyLineParticle() {
    }

    public AssemblyLineParticle(int x, int y, int alpha) { 
        speed = Math.random() * 20.0;
        angle = Math.random() * 60 + 60.0;
        this.x = x + 15;
        this.y = y + 40;
        this.alpha = alpha;
    }
    
    public void update() { 
        switch(directionNr) {
            case 0: {
                x = x + speed * Math.cos((Math.PI/180)*angle) * t;
                y = y + speed * Math.sin((Math.PI/180)*angle) - 0.5 * 9.8 * Math.pow(t, 2.0);
                break;
            } 
            case 1: {
                x = x - speed * Math.cos((Math.PI/180)*angle) * t;
                y = y - speed * Math.sin((Math.PI/180)*angle) - 0.5 * 9.8 * Math.pow(t, 2.0);
                break;
            }
            case 2: {
                x = x + speed * Math.cos((Math.PI/180)*angle) * t;
                y = y + speed * Math.sin((Math.PI/180)*angle) - 0.5 * 9.8 * Math.pow(t, 2.0);
                break;
            }
            case 3: {
                x = x - speed * Math.cos((Math.PI/90)*angle) * t;
                y = y - speed * Math.sin((Math.PI/90)*angle) - 0.5 * 9.8 * Math.pow(t, 2.0);               
                break;
            }
        }
        t += 0.20;    
        alpha -= 25;        
    } 
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setT(int t) {
        this.t = t;
    }
    
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    
    public int getAlpha() {
        return alpha;
    }
    
    public void setDirection(int directionNr) {
        this.directionNr = directionNr;
    }
    
    public void drawParticle(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(150,150,150,alpha));
        g2.fill(new Ellipse2D.Double(x,y,10,10));
    }
    
}
