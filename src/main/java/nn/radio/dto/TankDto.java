package nn.radio.dto;


import nn.radio.client.model.ClientTank;
import nn.radio.server.model.ServerTank;

import static nn.radio.Constants.*;

public class TankDto extends UnitDto {
    public float Y;
    public float X;
    public float alpha;

    float deltaX = 0.0F;
    float deltaY = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 1.4F;
    float speed = 1.45F;

    public boolean isFocusable = false;
    public boolean isAlive = true;

    public static float TANK_HEIGHT = 109F;
    public static float TANK_WIDTH = 82F;
    public static int BG_BORDER = 3;

    public ToreDto toreDto;

    public static ServerTank fromTank (ClientTank t) {
        ServerTank dto = new ServerTank();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.setTore(ToreDto.fromClientTore(t.tore));
        return dto;
    }

    public static TankDto fromTank (ServerTank t) {
        TankDto dto = new TankDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.isFocusable = t.isFocusable();
        dto.toreDto = ToreDto.fromServerTore(t.getTore());
        dto.isAlive = t.alive;
        return dto;
    }

    public void move () {
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
    }
}
