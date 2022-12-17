package nn.radio.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Torre {
    private java.util.List<Charge> chargeList = new ArrayList<>();
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage img;

    float Y;
    float X;
    public static float TORRE_HEIGHT = Tank.TANK_HEIGHT/3;
    public static float TORRE_WIDTH = Tank.TANK_WIDTH/3;
    float alpha = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 0.2F;
    private boolean isFocusable;

    public Torre (float x, float y) {
        this.X = x;
        this.Y = y;

        setFocusable(false);
        URL imgURLActive = getClass().getResource("/torre1.png");
        URL imgURLNonActive = getClass().getResource("/torreNotActive.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            img = imgActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw (Graphics g, float baseX, float baseY, float baseAlpha) {
        Graphics2D g2d = (Graphics2D)g;
        X = baseX;
        Y = baseY;
        alpha = alpha + deltaAlpha;
        g2d.rotate(Math.toRadians(alpha), (baseX + TORRE_HEIGHT/4), (baseY + TORRE_WIDTH/2));
        g.drawImage(img, (int) (baseX ), (int) (baseY ), (int) TORRE_HEIGHT, (int) TORRE_WIDTH, null);
        g2d.rotate(Math.toRadians(-(alpha)), (baseX + TORRE_HEIGHT/4), (baseY + TORRE_WIDTH/2));
    }

    public void drawCharges(Graphics g){
        chargeList.removeIf(charge -> !charge.alive);
        chargeList.forEach(charge -> {
            charge.draw(g, chargeList);
        });
    }

    public void shoot(float baseAlpha){
        chargeList.add(new Charge(X, Y, alpha+baseAlpha));
    }

    public void turnContrClockArrowDirection (){
        deltaAlpha = - speedAlpha;
    }

    public void turnByClockArrowDirection (){
        deltaAlpha = speedAlpha;
    }

    public void zeroSpeedAlpha(){
        deltaAlpha = 0;
    }

    public void setFocusable (boolean b) {
        isFocusable = b;
    }

    public boolean isFocusable () {
        return isFocusable;
    }

    public List<Charge> getChargeList () {
        return chargeList;
    }


}
