package nn.radio.client.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClientTorre {
    private java.util.List<ClientCharge> clientChargeList = new ArrayList<>();
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage img;

    float Y;
    float X;
    public static float TORRE_HEIGHT = ClientTank.TANK_HEIGHT/3;
    public static float TORRE_WIDTH = ClientTank.TANK_WIDTH/3;
    float alpha = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 0.2F;
    private boolean isFocusable;

    public ClientTorre (float x, float y) {
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
        move(baseX, baseY);
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
        g.drawImage(img, (int) (X ), (int) (Y ), (int) TORRE_HEIGHT, (int) TORRE_WIDTH, null);
        g2d.rotate(Math.toRadians(-(alpha)), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
    }

    private void move (float baseX, float baseY) {
        X = baseX;
        Y = baseY;
        alpha = alpha + deltaAlpha;
    }

    public void drawCharges(Graphics g){
        clientChargeList.removeIf(clientCharge -> !clientCharge.alive);
        clientChargeList.forEach(clientCharge -> {
            clientCharge.draw(g, clientChargeList);
        });
    }

    public void shoot(float baseAlpha){
        clientChargeList.add(new ClientCharge(X, Y, alpha+baseAlpha));
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

    public List<ClientCharge> getChargeList () {
        return clientChargeList;
    }


}
