package nn.radio.model;

import com.sun.security.auth.module.KeyStoreLoginModule;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;
import static nn.radio.model.Constants.*;

public class TankiApplication {
    public static void main(String[] args) throws InterruptedException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd = gs[1];

        Scena scena1 = new Scena();
        JFrame frame = new JFrame(gd.getDefaultConfiguration());
        frame.setTitle("TANKS MODEL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame.setBackground(Color.pink);
        frame.setResizable(true);

        frame.add(scena1);
        frame.setVisible(true);
    }
}
