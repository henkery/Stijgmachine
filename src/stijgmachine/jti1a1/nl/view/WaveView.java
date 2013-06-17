package stijgmachine.jti1a1.nl.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import stijgmachine.jti1a1.nl.objects.GameObject;

public class WaveView extends GameObject{
        public int x,y;
        private Image waveImage;
        private boolean yUp = true;

        public WaveView(Point2D point){
                super((int) point.getX(),(int) point.getY(),GameObject.ABSOLUTE);
                this.x = (int) point.getX();
                this.y = (int) point.getY();
                try {
                        waveImage = ImageIO.read(new File("./images/wave2.png"));
                } catch (IOException ex) {
                        System.out.println(ex);
                }
        }

        public void setLocation(Point2D point){
                this.x = (int) point.getX();
                this.y = (int) point.getY();
        }

        public int getX(){
                return x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

        private void moveUp(){
                y--;
        }

        private void moveDown(){
                y++;
        }

        public void move(){
                if (yUp){
                        if (y <= 950 && y >= 900)
                                moveUp();
                        if (y <= 900)
                                yUp = false;
                        }
                if (!yUp){
                        if (y <= 950 && y >= 900)
                                moveDown();
                        if (y >= 950)
                                yUp = true;
                }
        }

        public void draw(Graphics2D g, int height, int width, int x2, int y2){
                        g.drawImage(waveImage, (int)(x), getY() ,null);
                        System.out.println("drawing Waves");
        }


        @Override
        public int getID() {
                return 0;
        }

        @Override
        public void click() {

        }
}