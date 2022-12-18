package nn.radio.dto;

import nn.radio.client.model.ClientCharge;
import nn.radio.server.model.ServerCharge;

public class ChargeDto extends UnitDto{
    public float Y;
    public float X;
    public float alpha;
    public boolean alive = true;

    public static ServerCharge fromClientCharge (ClientCharge t) {
        ServerCharge dto = new ServerCharge(t.getX(), t.getY(), t.alpha);
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.alive = t.alive;
        return dto;
    }

    public static ChargeDto fromServerCharge (ServerCharge t) {
        ChargeDto dto = new ChargeDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.alive = t.alive;
        return dto;
    }
}
