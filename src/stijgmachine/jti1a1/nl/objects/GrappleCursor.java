package stijgmachine.jti1a1.nl.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import stijgmachine.jti1a1.nl.controller.Main;

import gameSteamCreation.Cursor;

public class GrappleCursor extends GameObject {

        private Image cursorImage;

        public GrappleCursor() {
                super(0, 0, GameObject.ABSOLUTE);
                cursorImage = new ImageIcon("./images/gameCursor.png").getImage();
        }

        @Override
        public void draw(Graphics2D g, int height, int width, int x2, int y2) {
                g.setColor(Color.BLACK);
                g.fillRect(x + 18, 0, 12, y-32);
                g.drawLine(x + 16, y, x+ 16, 0);
                g.drawImage(cursorImage, x,y-32,null);
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

        public void update(int x, int y) {
                this.x = x;
                this.y = y;
        }
}