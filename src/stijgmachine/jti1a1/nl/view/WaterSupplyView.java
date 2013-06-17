package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
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
        private ArrayList<WaveView> waveView = new ArrayList <WaveView>();
        public WaterSupplyView() {
                try {
                        imageBackground = ImageIO.read(new File(
                                        "./images/backgroundPipeGame.png"));
                } catch (IOException ex) {
                        System.out.println(ex);
                }
                for (int i = 1; i > 11; i++){
                        waveView.add(new WaveView(new Point2D.Double (i * 80, 920)));
                }
        }

        public void paintComponent(Graphics g) {
                if (count > 300) {
                        imageEventView.add(new RandomImageView());
                        count = 0;
                }
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(imageBackground, 0, 0, null);

                drawObjects(Main.getObjects(), g2);

                for (RandomImageView riv : imageEventView) {
                        riv.randomRainDraw(g);
                }

                Iterator<RandomImageView> it = imageEventView.iterator();

                while (it.hasNext()) {
                        RandomImageView o = (RandomImageView) it.next();
                        if (o.getY() > Main.resY)
                                it.remove();
                }
                count += Math.random() * 10;
        }

        @Override
        public int getID() {
                return 0;
        }

}