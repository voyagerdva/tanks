package nn.radio.client.view;

import nn.radio.client.model.ClientTank;
import nn.radio.client.model.ClientUser;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;
import nn.radio.dto.TankDto;
import nn.radio.server.ServerThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener {

    Map<String, ClientTank> tankMap = new HashMap<>();
    Map<String, ClientUser> userMap = new HashMap<>();

    public ServerThread tankTread;
//===========================================================================

    public Scena () {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);
    }

    public void setTankMap(Map<String, ClientTank> tnkMap){
        tnkMap.values().forEach(t -> {
            createTank(t.clientUser.id, t.clientUser.name, t.id, t.X, t.Y);
        });
    }

    public ClientTank createTank(ClientUser clientUser, float x, float y){
        ClientTank clientTank = new ClientTank(UUID.randomUUID().toString(), clientUser,x, y);
        tankMap.put(clientTank.getId(), clientTank);
        return clientTank;
    }

    public ClientTank createTank(String userId,String name, String tankId, float x, float y){
        ClientTank clientTank = new ClientTank(tankId, createUser(userId, name),x, y);
        tankMap.put(clientTank.getId(), clientTank);
        return clientTank;
    }

    public ClientUser createUser(String userId, String name){
        ClientUser clientUser = new ClientUser(userId, name);
        userMap.put(clientUser.id, clientUser);
        return clientUser;
    }

    public void updateTankMapWithDto(Map<String, TankDto> map){
        map.values().forEach(dto->{
            tankMap.get(dto.id).update(dto);
        });
    }

    @Override
    public void paintComponent (Graphics g) {
        // отрисовка всех объектов
        //intersectChargeAndTanks();
        tankMap.values().forEach(t -> t.draw(g));
        repaint();
    }

    private void intersectChargeAndTanks () {
        tankMap.values().forEach(clientTank -> {
            tankMap.values().forEach(t -> {
                if(!t.equals(clientTank)) {
                    t.clientChargeList.forEach(charge -> {
                        if(clientTank.intersect(charge)){
                            clientTank.makeDead();
                            charge.alive = false;
                            return;
                        }
                    });
                }
            });
        });
    }


//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        tankTread.keyPressed(KeyEventDto.fromKeyEvent(e));
    }

    @Override
    public void keyReleased (KeyEvent e) {
        tankTread.keyReleased(KeyEventDto.fromKeyEvent(e));
    }

    @Override
    public void keyTyped (KeyEvent e) {
    }


//===================================================================================

    @Override
    public void mouseClicked (MouseEvent e) {
        tankTread.mouseClicked(MouseEventDto.fromMouseEvent(e));
    }


    @Override
    public void actionPerformed (ActionEvent e) {
    }

    @Override
    public void mousePressed (MouseEvent e) {
    }

    @Override
    public void mouseReleased (MouseEvent e) {
    }

    @Override
    public void mouseEntered (MouseEvent e) {
    }

    @Override
    public void mouseExited (MouseEvent e) {
    }

//====================================================================================
}
