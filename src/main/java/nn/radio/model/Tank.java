package nn.radio.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static nn.radio.model.Constants.*;

public class Tank {

    public java.util.List<Charge> chargeList;
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage imgDaed;
    private BufferedImage img;

    float Y;
    float X;
    float alpha = 0.0F;
    ;
    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 0.4F;
    float speed = 0.45F;

    public static float TANK_HEIGHT = 109F;
    public static float TANK_WIDTH = 82F;
    public static int BG_BORDER = 3;

    private boolean alreadyClicked = false;
    private boolean isFocusable;
    private Torre tore;

    public Tank (float x, float y) {
        this.X = x;
        this.Y = y;
        tore = new Torre(x, y);
        chargeList = tore.getChargeList();
        setFocusable(false);
        URL imgURLActive = getClass().getResource("/tankActive2.png");
        URL imgURLNonActive = getClass().getResource("/tankNotActive2.png");
        URL imgURLDaed = getClass().getResource("/tankDead.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            imgDaed = ImageIO.read(imgURLDaed);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(BACKGROUND_COLOR);
        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.fillRect((int) X - BG_BORDER, (int) Y - BG_BORDER, (int) TANK_HEIGHT + BG_BORDER, (int) TANK_WIDTH + BG_BORDER);
        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        if (X >= SCENA_WIDTH - TANK_HEIGHT - 10) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_WIDTH - TANK_HEIGHT - 15;
        }

        if (X < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            X = SCENA_BORDER + 15;
        }

        if (Y >= SCENA_HEIGTH - TANK_WIDTH - 10) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_HEIGTH - TANK_WIDTH - 15;
        }

        if (Y < SCENA_BORDER) {
            deltaX = 0;
            deltaY = 0;
            Y = SCENA_BORDER + 15;
        }

        X = X + deltaX;
        Y = Y + deltaY;
        alpha = alpha + deltaAlpha;
        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));

        g.drawImage(img, (int) X, (int) Y, (int) TANK_HEIGHT, (int) TANK_WIDTH, null);
        tore.draw(g, (X + TANK_HEIGHT / 2.4F), (Y + TANK_WIDTH / 3), alpha);
        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        tore.drawCharges(g);
    }

    public void keyEventPressed (KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                deltaAlpha = -speedAlpha;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                deltaAlpha = speedAlpha;
                break;
            }
            case KeyEvent.VK_UP: {
                deltaX = (float) Math.cos(Math.toRadians(alpha)) * speed;
                deltaY = (float) Math.sin(Math.toRadians(alpha)) * speed;
                break;
            }
            case KeyEvent.VK_DOWN: {
                deltaX = -(float) Math.cos(Math.toRadians(alpha)) * speed;
                deltaY = -(float) Math.sin(Math.toRadians(alpha)) * speed;
                break;
            }
            case KeyEvent.VK_Q: {
                tore.turnContrClockArrowDirection();
                break;
            }
            case KeyEvent.VK_W: {
                tore.turnByClockArrowDirection();
                break;
            }
            case KeyEvent.VK_SPACE: {
                tore.shoot(alpha);
                break;
            }
        }
    }

    public void keyEventReleased (KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                deltaAlpha = 0.0F;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                deltaAlpha = 0.0F;
                break;
            }
            case KeyEvent.VK_UP: {
                deltaX = 0;
                deltaY = 0;
                break;
            }
            case KeyEvent.VK_DOWN: {
                deltaX = 0;
                deltaY = 0;
                break;
            }
            case KeyEvent.VK_Q: {
                tore.zeroSpeedAlpha();
                break;
            }
            case KeyEvent.VK_W: {
                tore.zeroSpeedAlpha();
                break;
            }
        }
    }

    public void mouseEventClicked (MouseEvent e) {
        if ((e.getPoint().getX() <= X + TANK_HEIGHT)
                && (e.getPoint().getX() >= X)
                && (e.getPoint().getY() <= Y + TANK_WIDTH)
                && (e.getPoint().getY() >= Y)
        ) {
            setFocusable(true);
            img = imgActive;
            if (alreadyClicked) {
                alreadyClicked = false;
            } else {
                alreadyClicked = true;
            }
        } else {
            setFocusable(false);
            img = imgNonActive;
        }
    }

    public void setFocusable (boolean b) {
        isFocusable = b;
    }

    public boolean isFocusable () {
        return isFocusable;
    }

    public boolean intersect (Charge charge) {
        if ((charge.getX() <= X + TANK_HEIGHT)
                && (charge.getX() >= X)
                && (charge.getY() <= Y + TANK_WIDTH)
                && (charge.getY() >= Y)
        ) {
            return true;
        }
        return false;
    }

    public void makeDead () {
        img = imgDaed;
    }
}
