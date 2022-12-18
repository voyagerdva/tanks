package nn.radio.dto;

import nn.radio.server.model.ServerTank;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseEventDto {
    private int x;
    private int y;

    public Point getPoint() {
        return new Point(x, y);
    }

    public static MouseEventDto fromMouseEvent(MouseEvent e){
        MouseEventDto dto = new MouseEventDto();
        dto.x = e.getX();
        dto.y = e.getY();
        return dto;
    }
}
