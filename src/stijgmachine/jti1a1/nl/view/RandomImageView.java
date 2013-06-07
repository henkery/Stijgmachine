package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;

public class RandomImageView {
	public int x,y;
	private Image imageWaterDrops;
	
	public RandomImageView(){
		setRandomX();
		this.y = 0;
		try {
			imageWaterDrops = ImageIO.read(new File("./images/waterDrop.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
	
	public int getX(){
		return x;
	}
	
	public void setRandomX(){
		this.x = (int)(Math.random() * Main.resX);
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void moveYDown(){
		this.y ++;
	}

	public void randomRainDraw(Graphics g){
			g.drawImage(imageWaterDrops, (int)(x), getY() ,null);
			moveYDown();
	}
	
	public void randomSteamDraw(Graphics g){
		
	}
}
