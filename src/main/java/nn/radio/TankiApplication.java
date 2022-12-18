package nn.radio;

import nn.radio.client.model.ClientTank;
import nn.radio.client.model.ClientUser;
import nn.radio.client.view.Scena;
import nn.radio.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static nn.radio.Constants.*;

public class TankiApplication {
    public static void main(String[] args) throws InterruptedException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];
        GraphicsDevice gd1 = gs[1];
        List<Scena> scenaList = new ArrayList<>();
        Map<String, ClientTank> tankMap = new HashMap<>();
        Map<String, ClientUser> userMap = new HashMap<>();
        tankMap.put("1U", createTank( "1U","Andy","1T",200F, 100F));
        tankMap.put("2U", createTank( "2U","Kirry","2T",200F, 200F));


        ServerThread tankThread = new ServerThread();
        tankThread.updateTankMap(tankMap);
        tankThread.updateScenaList(scenaList);

        Scena scena0 = new Scena();
        scena0.tankTread = tankThread;
        scena0.setTankMap(tankMap);
        scenaList.add(scena0);

        JFrame frame0 = new JFrame(gd0.getDefaultConfiguration());
        frame0.setTitle("TANKS MODEL0");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame0.setBackground(BACKGROUND_COLOR);
        frame0.setResizable(true);
        frame0.add(scena0);
        frame0.setVisible(true);


        Scena scena1 = new Scena();
        scena1.tankTread = tankThread;
        scena1.setTankMap(tankMap);
        scenaList.add(scena1);

        JFrame frame1 = new JFrame(gd1.getDefaultConfiguration());
        frame1.setTitle("TANKS MODEL1");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame1.setBackground(BACKGROUND_COLOR);
        frame1.setResizable(true);
        frame1.add(scena1);
        frame1.setVisible(true);

        tankThread.start();
    }

    public static ClientTank createTank(String userId,String name, String tankId, float x, float y){
        ClientTank clientTank = new ClientTank(tankId, createUser(userId, name),x, y);
        return clientTank;
    }

    public static ClientUser createUser(String userId, String name){
        ClientUser clientUser = new ClientUser(userId, name);
        return clientUser;
    }
}
