package nn.radio.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static nn.radio.model.Constants.*;

public class Tank {

    private final BufferedImage img1;
    private final BufferedImage img1act;
     BufferedImage img;

    float Y;
    float X;
    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 0.25F;
    float alpha = 45.0F;

    private boolean alreadyClicked = false;
    private boolean isFocusable;

    public Tank(float x, float y) throws IOException {
        URL imgURL1 = getClass().getResource("/tank1.png");
        URL imgURL2 = getClass().getResource("/tank1act.png");
        img1 = ImageIO.read(imgURL1);
        img1act = ImageIO.read(imgURL2);
        img = img1;

        this.X = x;
        this.Y = y;

        this.setFocusable(false);
    }

    private void setFocusable(boolean b) {
        isFocusable = b;
    }

    public void move(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(Math.toRadians(alpha), TANK_WIDTH / 2, TANK_HEIGHT / 2);
        g2d.setColor(backgroundColor);
        g2d.fillRect((int) X, (int) Y, (int) TANK_WIDTH, (int) TANK_HEIGHT);

        if (X >= SCENA_WIDTH - TANK_WIDTH - 10) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_WIDTH - TANK_WIDTH - 15;
        }

        if (X < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_BORDER + 15;
        }

        if (Y >= SCENA_HEIGTH - TANK_HEIGHT - 10) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_HEIGTH - TANK_HEIGHT - 15;
        }

        if (Y < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_BORDER + 15;
        }

        X = X + deltaX;
        Y = Y + deltaY;
        g2d.setColor(backgroundColor);


        g2d.rotate(Math.toRadians(-alpha), TANK_WIDTH / 2, TANK_HEIGHT / 2);
        g2d.fillRect((int) X, (int) Y, (int) TANK_WIDTH, (int) TANK_HEIGHT);
        g2d.drawImage((Image) img, (int) X, (int) Y, (int) TANK_WIDTH, (int) TANK_HEIGHT, null);

    }

//    public void turn(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.rotate(alpha);
//    }

    public void keyEventPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = -speed;
            deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = speed;
            deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = -speed;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = speed;
        }

        if (e.getKeyCode() == KeyEvent.VK_END) {
//            this.turn();
        }


    }

    public void keyEventReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
        System.out.println(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            deltaX = 0;
            deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            deltaX = 0;
            deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            deltaX = 0;
            deltaY = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            deltaX = 0;
            deltaY = 0;
        }
    }

    public void  mouseEventClicked(MouseEvent e) {
        if ((e.getPoint().getX() <= X + TANK_WIDTH)
                && (e.getPoint().getX() >= X)
                && (e.getPoint().getY() <= Y + TANK_HEIGHT)
                && (e.getPoint().getY() >= Y)
        ) {
            this.setFocusable(true);
            img = img1act;
            System.out.printf("CLICKED on x=%s  y=%s\n", e.getPoint().getX(), e.getPoint().getY());

            if (alreadyClicked) {
                alreadyClicked = false;
            } else {
                alreadyClicked = true;
            }
        } else {
            img = img1;
            this.setFocusable(false);
        }
    }

    public boolean isFocusable() {
        return isFocusable;
    }
}
