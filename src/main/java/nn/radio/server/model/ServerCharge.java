package nn.radio.server.model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import static nn.radio.Constants.*;
import static nn.radio.Constants.SCENA_BORDER;

public class ServerCharge {
    public String id;
    public float Y;
    public float X;
    public float alpha = 0.0F;
    public static float CHARGE_HEIGHT = 100F;
    public static float CHARGE_WIDTH = 20F;
    public boolean alive;

    float deltaAlpha = 0.0F;
    float speedAlpha = 0.3F;

    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float speed = 20.0F;

    public ServerCharge (float x, float y, float alpha) {
        this.id = UUID.randomUUID().toString();
        this.X = x;
        this.Y = y;
        this.alpha = alpha;
        deltaX = (float) (Math.cos(Math.toRadians(alpha))*speed);
        deltaY = (float) (Math.sin(Math.toRadians(alpha))*speed);
        System.out.println("X = " + X);
    }

    public void move () {
        if ((X >= SCENA_WIDTH) || (X < SCENA_BORDER) || (Y >= SCENA_HEIGTH) || (Y < SCENA_BORDER) ){
            alive = false;
        }

        X = X + deltaX;
        Y = Y + deltaY;
    }
}
