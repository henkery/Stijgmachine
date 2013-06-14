package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;

public class RandomImageView {
	public int x,y,speed;
	private Image imageWaterDrops;
	
	public RandomImageView(){
		setRandomX();
		this.y = 0;
		this.speed = (int) (Math.random()* 5);
		this.speed =+ 2;
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
		this.y += speed;
	}
	public void setSpeed(int speed){
		this.speed =speed;
	}

	public void randomRainDraw(Graphics g){
			g.drawImage(imageWaterDrops, (int)(x), getY() ,null);
			moveYDown();
	}
}
