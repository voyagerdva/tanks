package nn.radio.dto;

import nn.radio.client.model.ClientTorre;
import nn.radio.server.model.ServerTore;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ToreDto extends UnitDto{

    public float Y;
    public float X;
    public float alpha;
    public   float deltaAlpha;
    public java.util.List<ChargeDto> clientChargeList = new ArrayList<>();


    public static ServerTore fromClientTore (ClientTorre t) {
        ServerTore dto = new ServerTore();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.clientChargeList = t.getChargeMap().values().stream()
                .map(ch -> ChargeDto.fromClientCharge(ch))
                .collect(Collectors.toList());
        return dto;
    }

    public static ToreDto fromServerTore (ServerTore t) {
        ToreDto dto = new ToreDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.clientChargeList = t.clientChargeList.stream()
                .map(ch -> ChargeDto.fromServerCharge(ch))
                .collect(Collectors.toList());
        return dto;
    }
}
