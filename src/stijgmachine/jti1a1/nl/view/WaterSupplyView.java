package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.controller.Main;

public class WaterSupplyView extends MiniGameView {
	private Image imageBackground;
	private int y = 0;
	private double count = 0;
	private ArrayList<RandomImageView> imageEventView = new ArrayList<RandomImageView>();

	public WaterSupplyView() {
		try {
			imageBackground = ImageIO.read(new File("./images/backgroundPipeGame.png"));
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public void paintComponent(Graphics g) {
		if (count > 500){
			imageEventView.add(new RandomImageView());
			count = 0;
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(imageBackground, 0, 0, null);

		for (RandomImageView riv : imageEventView){
			riv.randomRainDraw(g);
			}
		
			Iterator it = imageEventView.iterator();

			while (it.hasNext()){
				RandomImageView o = (RandomImageView)it.next();
				if( o.getY() > Main.resY)
					it.remove();
			}
	
		
		drawObjects(Main.getObjects(), g2);
		count += Math.random() * 5;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
