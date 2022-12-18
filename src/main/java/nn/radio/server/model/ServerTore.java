package nn.radio.server.model;

import nn.radio.client.model.ClientCharge;
import nn.radio.client.model.ClientTank;

import java.util.ArrayList;

public class ServerTore {
    public java.util.List<ServerCharge> clientChargeList = new ArrayList<>();
    public String id;
    public float Y;
    public float X;
    public static float TORRE_HEIGHT = ClientTank.TANK_HEIGHT/3;
    public static float TORRE_WIDTH = ClientTank.TANK_WIDTH/3;
    public   float alpha = 0.0F;
    public   float deltaAlpha = 0.0F;
    public float speedAlpha = 9.2F;

    public void move (float baseX, float baseY) {
        X = baseX;
        Y = baseY;
        alpha = alpha + deltaAlpha;
        clientChargeList.forEach( c -> c.move());
    }

    public void shoot(float baseAlpha){
        clientChargeList.add(new ServerCharge(X, Y, alpha + baseAlpha));
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

}
