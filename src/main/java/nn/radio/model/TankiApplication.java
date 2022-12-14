package nn.radio.model;

import com.sun.security.auth.module.KeyStoreLoginModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static java.lang.Thread.sleep;
import static nn.radio.model.Constants.*;

public class TankiApplication {
    public static void main(String[] args) throws InterruptedException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd = gs[1];

        JFrame frame = new JFrame(gd.getDefaultConfiguration());
        frame.setTitle("TANKS MODEL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame.setBackground(Color.pink);
        frame.setResizable(true);

        Scena scena1 = new Scena(100F, 100F, Color.DARK_GRAY);
        Scena scena2 = new Scena(400F, 400F,  Color.CYAN);
//        frame.setVisible(true);
        Scena[] list = {scena1, scena2};

        frame.add(scena1);
        frame.setVisible(true);

        frame.add(scena2);
        frame.setVisible(true);


//        int i = 2;
//
//        while (true) {
//
//            frame.add(list[i%2]);
//            frame.setVisible(true);
//            i += 1;
//            System.out.println(i % 2);
//            sleep(3000);
//        }


        JPanel panel = new JPanel();




    }
}
