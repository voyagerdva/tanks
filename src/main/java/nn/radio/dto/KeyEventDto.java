package nn.radio.dto;

import java.awt.event.KeyEvent;

public class KeyEventDto {
    int keyCode;
    public int getKeyCode () {
        return keyCode;
    }

    public static KeyEventDto fromKeyEvent(KeyEvent e){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        return dto;
    }

    private void setKeyCode (int keyCode) {
        this.keyCode = keyCode;
    }
}
