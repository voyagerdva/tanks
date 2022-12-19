package nn.radio.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static nn.radio.model.Constants.*;

public class Tank {

    private final BufferedImage img1;
    private final BufferedImage img1act;
     BufferedImage img;

    float SHIFT = 30;

    float Y;
    float X;
    float deltaX = 0.0F;
    float deltaY = 0.0F;

    double A = 0.0D;
    double deltaA = 4.0D;

    float speed = 0.4F;

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

    public boolean isFocusable() {
        return isFocusable;
    }

    public void move(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(backgroundColor);
        g2d.fillArc(
                (int) X - (int) SHIFT / 2,
                (int) Y - (int) SHIFT / 2,
                (int) TANK_WIDTH + (int) SHIFT,
                (int) TANK_HEIGHT + (int) SHIFT,
                (int) 0,
                (int) 360);

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

        g2d.fillArc(
                (int) X - (int) SHIFT / 2,
                (int) Y - (int) SHIFT / 2,
                (int) TANK_WIDTH + (int) SHIFT,
                (int) TANK_HEIGHT + (int) SHIFT,
                (int) 0,
                (int) 360);
        g2d.setColor(backgroundColor.brighter());
        g2d.rotate(Math.toRadians(A), X + TANK_WIDTH / 2, Y + TANK_HEIGHT / 2);

        g2d.drawImage((Image) img, (int) X, (int) Y, (int) TANK_WIDTH , (int) TANK_HEIGHT, null);

        g2d.rotate(Math.toRadians(-A), X + TANK_WIDTH / 2, Y + TANK_HEIGHT / 2);

    }

    public void keyEventPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            A = A - deltaA;

            System.out.println(A);
            System.out.println("sin(A): " + Math.sin(Math.toRadians(A)));
            System.out.println("cos(A): " + Math.cos(Math.toRadians(A)));
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            A = A + deltaA;

            System.out.println(A);
            System.out.println("sin(A): " + Math.sin(Math.toRadians(Math.abs(A))));
            System.out.println("cos(A): " + Math.cos(Math.toRadians(Math.abs(A))));
        }


        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("A = " + A);
            if (A > 0) {
                deltaX = (float) (speed * Math.sin(Math.toRadians(Math.abs(A))));
                deltaY = (float) (-speed * Math.cos(Math.toRadians(Math.abs(A))));
            }

            if (A <= 0) {
                deltaX = (float) (-speed * Math.sin(Math.toRadians(Math.abs(A))));
                deltaY = (float) (-speed * Math.cos(Math.toRadians(Math.abs(A))));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("A = " + A);

            if (A > 0) {
                deltaX = (float) (-speed * Math.sin(Math.toRadians(Math.abs(A))));
                deltaY = (float) (speed * Math.cos(Math.toRadians(Math.abs(A))));
            }

            if (A <= 0) {
                deltaX = (float) (speed * Math.sin(Math.toRadians(Math.abs(A))));
                deltaY = (float) (speed * Math.cos(Math.toRadians(Math.abs(A))));
            }
        }

    }

    public void keyEventReleased(KeyEvent e) {
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
            System.out.println("Угол равен: " + A);
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


}
