package nn.radio.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static nn.radio.model.Constants.*;
import static nn.radio.model.Constants.SCENA_BORDER;

public class Charge {


    public boolean alive = true;
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage img;

    float Y;
    float X;
    public static float CHARGE_HEIGHT = 100F;
    public static float CHARGE_WIDTH = 20F;
    float alpha = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 0.3F;

    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 1.5F;

    public Charge (float x, float y,  float alpha) {
        this.X = x;
        this.Y = y;
        this.alpha = alpha;
        URL imgURLActive = getClass().getResource("/charge1.png");
        URL imgURLNonActive = getClass().getResource("/charge1.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deltaX = (float) (Math.cos(Math.toRadians(alpha))*speed);
        deltaY = (float) (Math.sin(Math.toRadians(alpha))*speed);
    }

    public void draw (Graphics g, java.util.List<Charge> list) {
        if ((X >= SCENA_WIDTH) || (X < SCENA_BORDER) || (Y >= SCENA_HEIGTH) || (Y < SCENA_BORDER) ){
           alive = false;
        }

        X = X + deltaX;
        Y = Y + deltaY;
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha),  (int) X, (int) Y);
        g.drawImage(img, (int) X, (int) Y, (int) CHARGE_HEIGHT, (int) CHARGE_WIDTH, null);
        g2d.rotate(Math.toRadians(-alpha),  (int) X, (int) Y);
    }

    public float getX () {
        return X;
    }
    public float getY () {
        return Y;
    }
}
